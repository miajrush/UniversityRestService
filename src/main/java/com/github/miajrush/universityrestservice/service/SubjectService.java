package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Subject;
import com.github.miajrush.universityrestservice.repository.SubjectRepository;
import com.github.miajrush.universityrestservice.web.controller.LessonController;
import com.github.miajrush.universityrestservice.web.controller.ProgressController;
import com.github.miajrush.universityrestservice.web.controller.SubjectController;

import java.util.List;

/**
 * Used as a facade for {@link SubjectController}, {@link LessonController} and {@link ProgressController}. For more
 * information on the methods, see {@link SubjectRepository}.
 */
public interface SubjectService {
	List<Subject> findAll();
	
	Subject findById(Integer id);
	
	void save(Subject subject);
	
	void update(Integer id, String name, Integer hoursNumber);
	
	void delete(Subject subject);
}
