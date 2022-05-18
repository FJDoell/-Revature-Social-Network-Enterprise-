package project.controllers;

import java.util.ArrayList;
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

import project.model.CommentModel;
import project.service.CommentService;

@RestController
public class CommentController {
	static List<CommentModel> Commentlist = new ArrayList<>();

	@Autowired
	CommentService Service;

	@PostMapping(value = "/insertComment")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public CommentModel createComment(@RequestBody CommentModel comment, HttpSession session) {
		return Service.postComment(comment, session);

	}

	@GetMapping(value = "/getCommentsByPost/{id}")
	public List<CommentModel> GetCommentByPost(@PathVariable(value = "id") Integer ID) {
		return Service.GetCommentByPost(ID);
	}
	
	

}
