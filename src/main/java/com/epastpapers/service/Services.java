package com.epastpapers.service;

import java.io.IOException;
import java.util.List;

import com.epastpapers.PastPapers.Exams;
import com.epastpapers.repository.ExamRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class Services implements StorageService {
	
	@Autowired
	ExamRepo examRepo;
	@Override
	public void saveFile(MultipartFile File, Exams exams) {
		try {
			exams.setData(File.getBytes());
			exams.setDocType(File.getContentType());
			examRepo.save(exams);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
	@Override
	public Exams getFile(int id) {
		// TODO Auto-generated method stub
		return examRepo.findById(id).get();
	}
	@Override
	public List<Exams> getAllFiles() {
		// TODO Auto-generated method stub
		return (List<Exams>) examRepo.findAll();
	}

	

}
