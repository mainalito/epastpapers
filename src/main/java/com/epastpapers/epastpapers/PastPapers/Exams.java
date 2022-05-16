package com.epastpapers.epastpapers.PastPapers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exams {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 128, nullable = false)
	private String fileName;

	private String originalFileName;
	private String fileType;

	@Lob
	private byte[] data; //store file as an array in database

	private int year;

	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private FACULTY faculty;

	public Exams( String fileName, String fileType, byte[] data, int year, FACULTY faculty) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.year = year;
		this.faculty = faculty;
	}


}
