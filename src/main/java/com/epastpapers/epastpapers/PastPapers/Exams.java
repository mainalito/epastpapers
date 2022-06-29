package com.epastpapers.epastpapers.PastPapers;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

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
	private Date date;

	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private FACULTY faculty;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Exams exams = (Exams) o;
		return id != null && Objects.equals(id, exams.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
