package com.github.miajrush.universityrestservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Simple JavaBean domain object representing a student.
 */
@EqualsAndHashCode(of = "", callSuper = true)
@Data
@Entity
@Table(name = "students")
public class Student extends NamedEntity {
	@NotNull
	@Min(value = 1, message = "Too small")
	@Max(value = 5, message = "Too large")
	@Column(name = "course")
	private Integer course;
	
	@Pattern(regexp = "\\d{10}", message = "Must be 10 digits")
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Email(message = "Must be valid")
	@Column(name = "email")
	private String email;
	
	@NotNull
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "group_id")
	private Group group;
	
	public Student() {
	}
	
	public Student(Integer id) {
		this.id = id;
	}
	
	public Student(Integer id, String name, Integer course, String phoneNumber, String email, Group group) {
		this.id = id;
		this.name = name;
		this.course = course;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.group = group;
	}
}
