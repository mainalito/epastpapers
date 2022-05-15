package com.epastpapers.epastpapers.EpastpaperController;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.epastpapers.epastpapers.PastPapers.Exams;
import com.epastpapers.epastpapers.repository.ExamRepo;
import com.epastpapers.epastpapers.repository.FacultyRepo;
import com.epastpapers.epastpapers.service.Services;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



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

	@PostMapping("/exam/upload")
	public String saveProduct(@RequestParam("files") MultipartFile[] files, Exams exams) throws Exception {
		String downloadUrl = "";

		//fetch url
		downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/download")
						.path(String.valueOf(exams.getId()))
						.toUriString();
		for (MultipartFile file : files) {
			service.saveFile(file, exams);
		}
		return "redirect:/";
	}


@GetMapping("/downloadFile/{id}")
	public void downloadDocument(@PathVariable("id") Long id, HttpServletResponse response) {
		Exams exams = service.getFile(id);
		response.setContentType(MediaType.parseMediaType(exams.getFileType()).toString());
		String headerKey = HttpHeaders.CONTENT_DISPOSITION;
		String headerValue = "exam; filename=\"" + exams.getOriginalFileName() + "\"";
		response.setHeader(headerKey, headerValue);
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(exams.getData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @GetMapping("/downloadFile/{id}")
	// public void downloadDocument(@PathVariable("id") Long id, HttpServletResponse response) {
	// 	Exams exams = service.getFile(id);
	// 	response.setContentType("application/pdf");
	// 	String headerKey = "Content-Disposition";
	// 	String headerValue = "attachment; filename=" + exams.getName()+"."+exams.getDocType().replaceAll("application/","");
	// 	response.setHeader(headerKey, headerValue);
	// 	try {
	// 		ServletOutputStream outputStream = response.getOutputStream();
	// 		outputStream.write(exams.getData());
	// 	} catch (IOException e) {
	// 		// TODO Auto-generated catch block
	// 		e.printStackTrace();
	// 	}

	// }
}
