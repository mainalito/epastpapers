package com.epastpapers.epastpapers.PastPapers;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity

public class FACULTY {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length=45, nullable=false, unique=true)
	private String name;

	public FACULTY() {}
	public FACULTY(String name,Long id) {
		super();
		this.id = id;
		this.name = name;
	}
	public FACULTY(String name) {
		super();
		this.name = name;
	}

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "FACULTY [id=" + id + ", name=" + name + "]";
	}


}
