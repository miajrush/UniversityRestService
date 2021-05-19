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
	/**
	 * Find all {@link Subject}s.
	 *
	 * @return the collection of the {@link Subject} objects.
	 */
	List<Subject> findAll();
	
	/**
	 * Find {@link Subject} by ID.
	 *
	 * @param id provided {@link Subject} id.
	 * @return {@link Subject} with provided ID or null otherwise.
	 */
	Subject findById(Integer id);
	
	/**
	 * Save provided {@link Subject} entity.
	 *
	 * @param subject provided {@link Subject}.
	 */
	void save(Subject subject);
	
	/**
	 * Update {@link Subject} by provided parameters.
	 *
	 * @param id {@link Subject} ID.
	 * @param name {@link Subject} name.
	 * @param hoursNumber number of hours in the {@link Subject}.
	 */
	void update(Integer id, String name, Integer hoursNumber);
	
	/**
	 * Delete provided {@link Subject} entity.
	 *
	 * @param subject provided {@link Subject}.
	 */
	void delete(Subject subject);
}
