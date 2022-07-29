package com.epastpapers.epastpapers.PastPapers;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Exams {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 128, nullable = false)
	private String fileName;

	private String originalFileName;
	private String fileType;
	private Long fileSize;
	@Lob
	private byte[] data; //store file as an array in database


	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

	private Date date;

	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private FACULTY faculty;


	
}
