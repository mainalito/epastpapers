package com.epastpapers.epastpapers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.epastpapers.epastpapers.repository.ExamRepo;

@SpringBootTest
class EpastpapersApplicationTests {

	@Autowired private ExamRepo examRepo;
	@Test
	void contextLoads() {
		//examRepo.findByDateRanges("12mont").forEach(System.out::println);
	}

}
