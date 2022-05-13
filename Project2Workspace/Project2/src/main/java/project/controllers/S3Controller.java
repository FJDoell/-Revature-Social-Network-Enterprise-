package project.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import project.service.AccountService;
import project.service.PostService;

@RestController
public class S3Controller {
	PostService servicePost;
	AccountService serviceAccount;
	private AmazonS3Client myS3Client;
	
	@Autowired
	public S3Controller(PostService servicePost, AccountService serviceAccount, AmazonS3Client myS3Client) {
		super();
		this.servicePost = servicePost;
		this.serviceAccount = serviceAccount;
		this.myS3Client = myS3Client;
	}

	@CrossOrigin
	@PostMapping("/uploadImageUser")
	public ResponseEntity<?> uploadImageUser(@RequestParam(value = "image") MultipartFile image, @RequestParam int accountId) throws IOException {
		String key = UUID.randomUUID() + image.getName(); // unique key for the file
		File tempFile = new File(System.getProperty("java.io.tmpdir") + "/img/" + key);
		image.transferTo(tempFile); // Convert multipart file to File
		serviceAccount.updateImage(accountId, key);
		myS3Client.putObject("avatarimages2005", key, tempFile); // Upload file
		tempFile.deleteOnExit(); // delete temp file
		return ResponseEntity.created(URI.create(key)).build();
	}
	
	@CrossOrigin
	@PostMapping("/uploadImagePost")
	public ResponseEntity<?> uploadImagePost(@RequestParam(value = "image") MultipartFile image, @RequestParam int postId) throws IOException {
		String key = UUID.randomUUID() + image.getName(); // unique key for the file
		File tempFile = new File(System.getProperty("java.io.tmpdir") + "/img/" + key);
		image.transferTo(tempFile); // Convert multipart file to File
		servicePost.updatePostImage(postId, key);
		myS3Client.putObject("avatarimages2005", key, tempFile); // Upload file
		tempFile.deleteOnExit(); // delete temp file
		return ResponseEntity.created(URI.create(key)).build();
	}

	@CrossOrigin
	@GetMapping("/img/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws IOException {
		S3Object data = myS3Client.getObject("avatarimages2005", fileName); // fileName is key which is used while uploading the object 
        S3ObjectInputStream objectContent = data.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectContent);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        objectContent.close();
        return ResponseEntity
                .ok()
                .contentLength(bytes.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
	}
}
