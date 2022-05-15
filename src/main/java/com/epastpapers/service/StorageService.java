package com.epastpapers.service;

import java.util.List;

import com.epastpapers.PastPapers.Exams;

import org.springframework.web.multipart.MultipartFile;


public interface StorageService {
	void saveFile(MultipartFile File,Exams exams);
	Exams getFile(int id);
	List<Exams>getAllFiles();
}
