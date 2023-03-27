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
	@Test
	void mathRoundTest() {
		System.out.println(((int)Math.round(83.1234*100))/100.0);
		System.out.println(((int)Math.round(83.1254*100))/100.0);
		System.out.println(((int)Math.round(83.1274*100))/100.0);
	}

}
