package project.service;

import project.dao.PostDao;
import project.model.PostModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PostService {
	PostDao p;

	@Autowired
	public PostService(PostDao p) {
		super();
		this.p = p;
	}

	public void postPost(PostModel post) {
		p.save(post);

	}

	/**
	 * Given an ID and image URL string, set that in the DB.
	 * 
	 * @param id
	 * @param imgUrl
	 * @author darkm (Frederick)
	 */
	public void updatePostImage(int id, String imgUrl) {
		PostModel post = p.getById(id);
		post.setPostImage(imgUrl);
		p.save(post);
	}

	public List<PostModel> Readall() {
		return p.findAll();

	}
}
