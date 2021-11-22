package com.ironhack.discountservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DiscountServiceApplicationTests {

	@Autowired
	DiscountServiceApplication discountServiceApplication;

	@Test
	void contextLoads() {
		assertNotNull(discountServiceApplication);
	}

	@Test
	void main() {
		DiscountServiceApplication.main(new String[] {});
	}

}
