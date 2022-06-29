package com.epastpapers.epastpapers.EpastpaperController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import javax.print.MultiDocPrintJob;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.epastpapers.epastpapers.PastPapers.Exams;
import com.epastpapers.epastpapers.repository.ExamRepo;
import com.epastpapers.epastpapers.repository.FacultyRepo;
import com.epastpapers.epastpapers.service.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller

@AllArgsConstructor
public class ExamController {

	private final ExamRepo examRepo;

	private final FacultyRepo facultyRepo;

	private final Services service;

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
	public String saveProduct(@RequestParam("files") MultipartFile[] files,
			RedirectAttributes redirectAttributes, Exams exams,
			BindingResult result) throws Exception {
		String fileName = StringUtils.cleanPath(exams.getOriginalFileName());

		Arrays.asList(files)
				.forEach(file -> {
					try {
						service.saveFile(file, exams);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});

		redirectAttributes.addFlashAttribute("errorMessage", "You have successfully uploaded file");

		return "redirect:/exams/new";

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

	@GetMapping("/delete/exam/{id}")
	public String deleteDocument(@PathVariable("id") Long id) {

		Exams exam = service.getFile(id);
		service.deleteFile(id);
		return "redirect:/faculty/"+exam.getFaculty().getId();
	}
}
