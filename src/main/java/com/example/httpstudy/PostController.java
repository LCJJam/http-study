package com.example.httpstudy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/post/{id}")
	public Post getPost(@PathVariable Long id) {
		return postService.getPostById(id);
	}

	@GetMapping("/posts")
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}
}
