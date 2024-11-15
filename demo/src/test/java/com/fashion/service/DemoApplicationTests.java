package com.fashion.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DemoApplicationTests {

	 @Autowired
	    private FashionService fashionService;
	
	@Test
	public  void contextLoads() {
	     fashionService.clearCache();
	}

}
