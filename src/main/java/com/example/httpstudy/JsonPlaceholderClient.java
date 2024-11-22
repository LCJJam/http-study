package com.example.httpstudy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "jsonPlaceholderClient"
	, url = "https://jsonplaceholder.typicode.com"
	,configuration = CustomFeignConfig.class
)
public interface JsonPlaceholderClient {

	@GetMapping("/posts/{id}")
	Post getPostById(@PathVariable("id") Long id);

	@GetMapping("/posts")
	List<Post> getAllPosts();
}
