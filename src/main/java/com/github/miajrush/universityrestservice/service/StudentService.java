package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Group;
import com.github.miajrush.universityrestservice.model.Student;
import com.github.miajrush.universityrestservice.repository.StudentRepository;
import com.github.miajrush.universityrestservice.web.controller.GroupController;
import com.github.miajrush.universityrestservice.web.controller.ProgressController;
import com.github.miajrush.universityrestservice.web.controller.StudentController;

import java.util.List;

/**
 * Used as a facade for {@link StudentController}, {@link GroupController} and {@link ProgressController}. For more
 * information on the methods, see {@link StudentRepository}.
 */
public interface StudentService {
	List<Student> findAll();
	
	Student findById(Integer id);
	
	void save(Student student);
	
	void update(Integer id, String name, Integer course, String phoneNumber, String email, Group group,
	            Group oldGroup);
	
	void delete(Student student);
}
