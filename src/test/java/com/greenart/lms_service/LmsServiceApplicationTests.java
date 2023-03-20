package com.greenart.lms_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.lms_service.service.ScoreService;

@SpringBootTest
class LmsServiceApplicationTests {
	@Autowired ScoreService scoreService;

	@Test
	void contextLoads() {
		System.out.println(scoreService.getTestScore(1L, 2L));
	}

}
