package com.epastpapers.epastpapers.repository;
import java.util.List;

import javax.transaction.Transactional;

import com.epastpapers.epastpapers.PastPapers.Exams;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;


public interface ExamRepo extends CrudRepository<Exams, Long> {
	
	@Query("FROM Exams e WHERE e.faculty.id=:facultyIdFK")
	List<Exams> findByFacultyId(@PathVariable("facultyIdFK") Long facultyIdFK);
	
	
	List<Exams>findByfaculty_id(Long id);
	

}
