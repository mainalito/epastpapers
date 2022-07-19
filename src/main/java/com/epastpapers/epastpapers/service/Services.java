package com.epastpapers.epastpapers.service;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.epastpapers.epastpapers.PastPapers.Exams;
import com.epastpapers.epastpapers.repository.ExamRepo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@AllArgsConstructor
public class Services implements StorageService {


	private final ExamRepo examRepo;

	@Override
	public void saveFile(MultipartFile File, Exams exams) throws Exception {
		//List<String> fileExtensions = List.of(".pdf, .docx");
		
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(File.getOriginalFilename()));
		String subString = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		
		try {
			if (fileName.contains("..") ) {
				throw new Exception("Filename contains invalid path " + fileName);
			}
			else if(subString.toLowerCase().equals(".pdf")){

				exams.setOriginalFileName(fileName);
				exams.setData(File.getBytes());
				exams.setFileType(File.getContentType());
				exams.setFileSize(File.getSize());
				examRepo.save(exams);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Could not save file " + exams);
		}

	}

	@Override
	public Exams getFile(Long id) {
		// TODO Auto-generated method stub
		return examRepo.findById(id).orElseThrow(()-> new RuntimeException("no file found"));
	}

	@Override
	public List<Exams> getAllFiles() {
		// TODO Auto-generated method stub
		return (List<Exams>) examRepo.findAll();
	}

    public void deleteFile(Long id) {
		examRepo.deleteById(id);
    }

	@Override
	public List<Exams> weeklyReportList() {
		// TODO Auto-generated method stub
		return examRepo.findByDateRangesWeekly();
	}

	@Override
	public List<Exams> monthlyReportList() {
		// TODO Auto-generated method stub
		return examRepo.findByDateRangesMonthly();
	}

	@Override
	public List<Exams> yearlyReportList() {
		// TODO Auto-generated method stub
		return examRepo.findByDateRangesYearly();
	}
}
