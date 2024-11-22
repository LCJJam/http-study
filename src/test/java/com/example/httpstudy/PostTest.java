package com.example.httpstudy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
class PostTest {
	@Test
	void testPostCreation() {
		// Given: 새로운 Post 객체
		Post post = new Post(1L, 1L, "Title", "Body");

		// Then: 상태 검증
		assertThat(post.getUserId()).isEqualTo(1L);
		assertThat(post.getId()).isEqualTo(1L);
		assertThat(post.getTitle()).isEqualTo("Title");
		assertThat(post.getBody()).isEqualTo("Body");

	}
}
