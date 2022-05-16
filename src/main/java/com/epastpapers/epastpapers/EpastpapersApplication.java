package com.epastpapers.epastpapers;

import javax.annotation.PostConstruct;

import com.epastpapers.epastpapers.PastPapers.FACULTY;
import com.epastpapers.epastpapers.repository.FacultyRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;

@SpringBootApplication
public class EpastpapersApplication {

	@Autowired private  FacultyRepo facultyRepo;

	public static void main(String[] args) {
		SpringApplication.run(EpastpapersApplication.class, args);
	}



}
