package com.example.httpstudy;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.httpstudy.JsonPlaceholderClient;
import com.example.httpstudy.Post;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonPlaceholderClientWireMockTest {

	@Autowired
	private JsonPlaceholderClient jsonPlaceholderClient;

	private static WireMockServer wireMockServer;

	@BeforeEach
	void setUp() {
		wireMockServer = new WireMockServer(8081); // WireMock 서버 실행 포트 설정
		wireMockServer.start();

		// WireMock 서버의 URL을 Spring 컨텍스트에 주입
		WireMock.configureFor("localhost", 8081);
	}

	@AfterEach
	void tearDown() {
		wireMockServer.stop(); // 테스트 종료 후 WireMock 서버 종료
	}

	@Test
	void testGetPostById() {
		// Stub 설정: /posts/1에 대한 응답을 Mocking
		wireMockServer.stubFor(
			get(urlEqualTo("/post/1"))
				.willReturn(aResponse()
					.withHeader("Content-Type", "application/json")
					.withBody("""
                                    {
                                        "userId": 1,
                                        "id": 1,
                                        "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                                        "body": "quia et suscipit
                                        suscipit recusandae consequuntur expedita et cum
                                        reprehenderit molestiae ut ut quas totam
                                        nostrum rerum est autem sunt rem eveniet architecto"
                                    }
                                """))
		);

		String title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
		String body = "quia et suscipit\n"
			+ "suscipit recusandae consequuntur expedita et cum\n"
			+ "reprehenderit molestiae ut ut quas totam\n"
			+ "nostrum rerum est autem sunt rem eveniet architecto";

		// Feign 클라이언트를 사용하여 호출
		Post post = jsonPlaceholderClient.getPostById(1L);

		// 결과 검증
		assertThat(post.getId()).isEqualTo(1L);
		assertThat(post.getTitle()).isEqualTo(title);
		assertThat(post.getBody()).isEqualTo(body);

		// WireMock 검증
		// wireMockServer.verify(getRequestedFor(urlEqualTo("/post/1")));
	}
}
