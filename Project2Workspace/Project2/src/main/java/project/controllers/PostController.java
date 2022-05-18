package project.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import project.model.AccountModel;
import project.model.PostModel;
import project.service.PostService;

@RestController
public class PostController {
	PostService Service;

	@Autowired
	public PostController(PostService service) {
		super();
		Service = service;
	}

	@PostMapping(value = "/AddPost")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public PostModel createPost(@RequestBody PostModel post, HttpSession Session) {
		return Service.postPost(post, Session);
	}

	@PostMapping(value = "/ReadPost")
	public List<PostModel> readPost() {
		return Service.Readall();
	}

	
	
	@GetMapping(value = "/GetPostByCurrentUser")
	@ResponseBody
	public List<PostModel> GetPostByCurrentUser(HttpSession Session) {
		AccountModel H= (AccountModel) Session.getAttribute("currentUser");
		return Service.getPostsByUser(H.getAccountId());

	}
	
	@GetMapping(value = "/GetPostsByUser/{id}")
	public List<PostModel> GetPostsByUser(@PathVariable(name = "id") Integer id) {
		return Service.getPostsByUser(id);
	}
	
	@PostMapping("/likePostById/{id}")
	public PostModel likeById(@PathVariable(name = "id") Integer id) {
		return Service.LikePost(id);
	}
	
	@GetMapping(value = "/getPostCount")
	public long getPostCount() {
		return Service.getPostCount();
	}
		
	@GetMapping(value="/getPostsByPage/{page}")
	public List<PostModel> getPostsByPage(@PathVariable(name="page") Integer page) {
		return Service.getPostsByPage(page);
	}
	
}
