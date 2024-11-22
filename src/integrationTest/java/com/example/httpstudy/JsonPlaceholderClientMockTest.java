package com.example.httpstudy;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.httpstudy.JsonPlaceholderClient;
import com.example.httpstudy.Post;
import com.example.httpstudy.PostService;

@SpringBootTest
public class JsonPlaceholderClientMockTest {

	@Autowired
	private PostService postService;

	@MockBean
	private JsonPlaceholderClient jsonPlaceholderClient;

	@Test
	public void testGetPostById() {
		// Given: Mock 데이터 설정

		String title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";

		Post mockPost = new Post(1L, 1L, title, "Mock Body");
		when(jsonPlaceholderClient.getPostById(1L)).thenReturn(mockPost);

		// When: 서비스 호출
		Post post = postService.getPostById(1L);

		// Then: 결과 검증
		assertThat(post.getId()).isEqualTo(1L);
		assertThat(post.getTitle()).isEqualTo(title);
		assertThat(post.getBody()).isEqualTo("Mock Body");
	}
}
