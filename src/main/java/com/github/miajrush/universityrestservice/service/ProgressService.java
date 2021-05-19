package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Progress;
import com.github.miajrush.universityrestservice.model.Student;
import com.github.miajrush.universityrestservice.model.Subject;
import com.github.miajrush.universityrestservice.repository.ProgressRepository;
import com.github.miajrush.universityrestservice.web.controller.ProgressController;
import com.github.miajrush.universityrestservice.web.controller.SubjectController;

import java.util.List;

/**
 * Used as a facade for {@link ProgressController} and {@link SubjectController}. For more information on the methods,
 * see {@link ProgressRepository}.
 */
public interface ProgressService {
	List<Progress> findAll();
	
	Progress findById(Integer id);
	
	void save(Progress progress);
	
	void update(Integer id, Student student, Subject subject, Byte grade);
	
	void delete(Progress progress);
}
