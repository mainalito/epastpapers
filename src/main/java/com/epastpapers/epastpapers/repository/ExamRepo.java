package com.epastpapers.epastpapers.repository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.epastpapers.epastpapers.PastPapers.Exams;


public interface ExamRepo extends CrudRepository<Exams, Long> {

	@Query(value = "select * from exams where file_name LIKE %?1%", nativeQuery = true)
	List<Exams>findByfileNameLike(String fileName);
	
	@Query("FROM Exams e WHERE e.faculty.id=:facultyIdFK")
	List<Exams> findByFacultyId(@PathVariable("facultyIdFK") Long facultyIdFK);
	
	
	List<Exams>findByfaculty_id(Long id);

	//find exams by dates
	List<Exams> findByDateGreaterThan(Date date);
	

}
