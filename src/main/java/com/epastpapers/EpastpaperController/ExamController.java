package com.epastpapers.EpastpaperController;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.epastpapers.PastPapers.Exams;
import com.epastpapers.repository.ExamRepo;
import com.epastpapers.repository.FacultyRepo;
import com.epastpapers.service.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class ExamController {
	@Autowired
	ExamRepo examRepo;
	@Autowired
	FacultyRepo facultyRepo;
	@Autowired
	Services service;

	@GetMapping("/exams")
	public String fetchFiles(Model model) {
		model.addAttribute("docs", service.getAllFiles());
		return "exam-files";
	}

	@GetMapping("/exams/new")
	public String showNewRevisionPaperForm(Model model) {
		model.addAttribute("faculties", facultyRepo.findAll());
		model.addAttribute("exam", new Exams());
		return "exam-form";
	}

	@PostMapping("/exam/save")
	public String saveProduct(@RequestParam("files") MultipartFile[] files, Exams exams) {
		for (MultipartFile file : files) {
			service.saveFile(file, exams);
		}
		return "redirect:/";
	}

//	@GetMapping("/downloadFile/{id}")
//	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("id") int id) {
//		Exams exams = service.getFile(id);
//		System.out.println(exams);
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType(exams.getDocType()))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + exams.getName() + "\"")
//				.body(new ByteArrayResource(exams.getData()));
//
//	}

	@GetMapping("/downloadFile/{id}")
	public void downloadDocument(@PathVariable("id") int id, HttpServletResponse response) {
		Exams exams = service.getFile(id);
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + exams.getName()+"."+exams.getDocType().replaceAll("application/","");
		response.setHeader(headerKey, headerValue);
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(exams.getData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
