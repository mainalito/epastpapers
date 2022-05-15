package com.epastpapers.repository;
import java.util.List;

import com.epastpapers.PastPapers.Exams;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;


public interface ExamRepo extends CrudRepository<Exams, Integer> {
	
	@Query("FROM Exams e WHERE e.faculty.id=:facultyIdFK")
	List<Exams> findByFacultyId(@PathVariable("facultyIdFK") int facultyIdFK);
	
	List<Exams>findByfaculty_id(int id);
	

}
