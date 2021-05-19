package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.TeacherPosition;
import com.github.miajrush.universityrestservice.repository.TeacherPositionRepository;

import java.util.List;

/**
 * Used as a facade for a {@link TeacherController}. For more information on the methods, see {@link
 * TeacherPositionRepository}.
 */
public interface TeacherPositionService {
	List<TeacherPosition> findAll();
	
	TeacherPosition findById(Integer id);
	
	void save(TeacherPosition teacherPosition);
	
	void update(Integer id, String name);
	
	void delete(TeacherPosition teacherPosition);
}
