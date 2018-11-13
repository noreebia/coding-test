package org.sooyoungkim.codingtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sooyoungkim.codingtest.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodingTestApplicationTests {
	
	@Autowired
	PriceService priceService;

	@Test
	public void contextLoads() {
	}
}
