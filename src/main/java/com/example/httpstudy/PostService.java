package com.example.httpstudy;

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
}
