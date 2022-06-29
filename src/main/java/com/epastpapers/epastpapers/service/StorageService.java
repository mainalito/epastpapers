package com.epastpapers.epastpapers.service;

import java.util.List;

import com.epastpapers.epastpapers.PastPapers.Exams;

import org.springframework.web.multipart.MultipartFile;


public interface StorageService {
	void saveFile(MultipartFile File,Exams exams) throws Exception;
	Exams getFile(Long id);
	List<Exams>getAllFiles();
	void deleteFile(Long id);
}
