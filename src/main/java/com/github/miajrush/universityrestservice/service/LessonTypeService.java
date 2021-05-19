package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.LessonType;
import com.github.miajrush.universityrestservice.repository.LessonTypeRepository;
import com.github.miajrush.universityrestservice.web.controller.LessonController;

import java.util.List;

/**
 * Used as a facade for a {@link LessonController}. For more information on the methods, see {@link
 * LessonTypeRepository}.
 */
public interface LessonTypeService {
	List<LessonType> findAll();
	
	LessonType findById(Integer id);
	
	void save(LessonType lessonType);
	
	void update(Integer id, String name);
	
	void delete(LessonType lessonType);
}
