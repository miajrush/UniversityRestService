package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Teacher;
import com.github.miajrush.universityrestservice.model.TeacherPosition;
import com.github.miajrush.universityrestservice.repository.TeacherRepository;
import com.github.miajrush.universityrestservice.web.controller.LessonController;
import com.github.miajrush.universityrestservice.web.controller.TeacherController;

import java.util.List;

/**
 * Used as a facade for {@link TeacherController} and {@link LessonController}. For more information on the methods, see
 * {@link TeacherRepository}.
 */
public interface TeacherService {
	List<Teacher> findAll();
	
	Teacher findById(Integer id);
	
	void save(Teacher teacher);
	
	void update(Integer id, String name, String phoneNumber, TeacherPosition position);
	
	void delete(Teacher teacher);
}
