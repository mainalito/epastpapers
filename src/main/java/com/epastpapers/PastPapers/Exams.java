package com.epastpapers.PastPapers;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Exams {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length=128 , nullable=false)
	private String name;
	private String docType;
	@Lob
	private byte[] data;
	private int year;
	
	@ManyToOne
	@JoinColumn(name= "faculty_id")
	private FACULTY faculty;

	
	public Exams() {}
	
	public Exams(String name, String docType, byte[] data, int year) {
		super();
		this.name = name;
		this.docType = docType;
		this.data = data;
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public FACULTY getFaculty() {
		return faculty;
	}

	public void setFaculty(FACULTY faculty) {
		this.faculty = faculty;
	}
	

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Exams [id=" + id + ", name=" + name + ", docType=" + docType + ", data=" + Arrays.toString(data)
				+ ", year=" + year + ", faculty=" + faculty + "]";
	}

	
}
