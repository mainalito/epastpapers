package com.epastpapers.EpastpaperController;

import java.util.List;

import com.epastpapers.PastPapers.Exams;
import com.epastpapers.repository.ExamRepo;
import com.epastpapers.repository.FacultyRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class FacultyController {
	@Autowired
	FacultyRepo facultyRepo;
	@Autowired 
	ExamRepo examRepo;
	
	@GetMapping("/")
	public String showFaculties(Model model) {
		
		model.addAttribute("faculties",facultyRepo.findAll());
		return "index";
	}
	@GetMapping("/faculty/{id}")
	public String showFacultiesExams(@PathVariable int id,  Model model) {
		Exams exams = new Exams();
		List<Exams> s= examRepo.findByfaculty_id(id);
		s.forEach(r->System.out.println(r));
		
		model.addAttribute("facultyExam",s);

		return "faculty";
	}
}
