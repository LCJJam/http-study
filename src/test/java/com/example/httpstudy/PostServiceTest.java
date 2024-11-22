package com.example.httpstudy;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PostServiceTest {

	@Autowired
	private PostService postService;

	@MockBean
	private JsonPlaceholderClient jsonPlaceholderClient;

	@Test
	public void testGetPostById() {

		String title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
		String body = "quia et suscipit\n"
			+ "suscipit recusandae consequuntur expedita et cum\n"
			+ "reprehenderit molestiae ut ut quas totam\n"
			+ "nostrum rerum est autem sunt rem eveniet architecto";
		// Given: Mock 데이터를 설정
		Post mockPost = new Post(1L, 1L, title, body);
		when(jsonPlaceholderClient.getPostById(1L)).thenReturn(mockPost);

		// When: 서비스 메서드 호출
		Post post = postService.getPostById(1L);

		// Then: 결과 검증
		assertThat(post.getId()).isEqualTo(1L);
		assertThat(post.getTitle()).isEqualTo(title);
		assertThat(post.getBody()).isEqualTo(body);
	}
}
