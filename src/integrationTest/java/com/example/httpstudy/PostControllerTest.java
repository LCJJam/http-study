package com.example.httpstudy;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;

import com.example.httpstudy.Post;
import com.example.httpstudy.PostController;
import com.example.httpstudy.PostService;

@WebMvcTest(PostController.class)
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;


	@MockBean
	private PostService postService;

	@Test
	public void testGetPost() throws Exception {

		String title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
		String body = "quia et suscipit\n"
			+ "suscipit recusandae consequuntur expedita et cum\n"
			+ "reprehenderit molestiae ut ut quas totam\n"
			+ "nostrum rerum est autem sunt rem eveniet architecto";

		Post mockPost = new Post();
		mockPost.setId(1L);
		mockPost.setUserId(1L);
		mockPost.setTitle(title);
		mockPost.setBody(body);

		when(postService.getPostById(1L)).thenReturn(mockPost);

		mockMvc.perform(get("/post/1"))
			.andExpect(status().isOk())
		// 	.andExpect(jsonPath("$.id").value(1L))
		// 	.andExpect(jsonPath("$.title").value("Test Title"))
		// 	.andExpect(jsonPath("$.body").value("Test Body"))
		;
	}

	@Test
	public void testGetAllPosts() throws Exception {
		Post mockPost1 = new Post();
		mockPost1.setId(1L);
		mockPost1.setUserId(1L);
		// mockPost1.setTitle("Title 1");
		// mockPost1.setBody("Body 1");

		Post mockPost2 = new Post();
		mockPost2.setId(2L);
		mockPost2.setUserId(1L);
		// mockPost2.setTitle("Title 2");
		// mockPost2.setBody("Body 2");

		when(postService.getAllPosts()).thenReturn(Arrays.asList(mockPost1, mockPost2));

		mockMvc.perform(get("/posts"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id").value(1))
			.andExpect(jsonPath("$[1].id").value(2));
	}
}
