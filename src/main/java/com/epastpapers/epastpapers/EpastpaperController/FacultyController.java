package com.epastpapers.epastpapers.EpastpaperController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.epastpapers.epastpapers.PastPapers.Exams;
import com.epastpapers.epastpapers.PastPapers.FACULTY;
import com.epastpapers.epastpapers.repository.ExamRepo;
import com.epastpapers.epastpapers.repository.FacultyRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FacultyController {
	@Autowired
	FacultyRepo facultyRepo;
	@Autowired
	ExamRepo examRepo;

	@GetMapping("/")
	public String showFaculties(Model model) {

		model.addAttribute("faculties", facultyRepo.findAll());
		return "index";
	}

	@GetMapping("/search")
	public String searchLikeStudents(@RequestParam("query") String query, Model model) {

		model.addAttribute("results", examRepo.findByfileNameLike(StringUtils.capitalize(query)));
		return "search";
	}

	@GetMapping("/filter/{id}")
	public String filterWithinDates(@PathVariable Long id,@RequestParam ("startDate") String startDate, @RequestParam("endDate") String endDate,
	 Model model){
		
		SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");  
		try {
			model.addAttribute("filteredResults", examRepo.findByDateBetweenAndfaculty_id(formatter2.parse(startDate),
			formatter2.parse(endDate),id));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "faculty";
	}

	@GetMapping("/faculty/{id}")
	public String showFacultiesExams(@PathVariable Long id, Model model,RedirectAttributes redirectAttributes) {

		List<Exams> s = examRepo.findByfaculty_id(id);
		FACULTY faculties = facultyRepo.findById(id).orElseThrow(()->new RuntimeException("no faculty found"));
		model.addAttribute("faculty",faculties);
		
		if(!s.isEmpty()){
			model.addAttribute("facultyExam", s);

		}
		else{
			redirectAttributes.addFlashAttribute("errorMessage", "No exams found" );
		}
		return "faculty";
	}
}
