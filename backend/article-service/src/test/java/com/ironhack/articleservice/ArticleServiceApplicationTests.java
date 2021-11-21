package com.ironhack.articleservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ArticleServiceApplicationTests {

	@Autowired
	ArticleServiceApplication articleServiceApplication;

	@Test
	void contextLoads() {
		assertNotNull(articleServiceApplication);
	}

	@Test
	void main() {
		ArticleServiceApplication.main(new String[] {});
	}

}
