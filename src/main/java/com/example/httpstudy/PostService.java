package com.example.httpstudy;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PostService {

	private final JsonPlaceholderClient jsonPlaceholderClient;

	public PostService(JsonPlaceholderClient jsonPlaceholderClient) {
		this.jsonPlaceholderClient = jsonPlaceholderClient;
	}

	public Post getPostById(Long id) {
		return jsonPlaceholderClient.getPostById(id);
	}

	public List<Post> getAllPosts() {
		return jsonPlaceholderClient.getAllPosts();
	}
}
