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
	/**
	 * Find all {@link Progress}.
	 *
	 * @return the collection of the {@link Progress} objects.
	 */
	List<Progress> findAll();
	
	/**
	 * Find {@link Progress} by ID.
	 *
	 * @param id provided {@link Progress} id.
	 * @return {@link Progress} with provided ID or null otherwise.
	 */
	Progress findById(Integer id);
	
	/**
	 * Save provided {@link Progress} entity.
	 *
	 * @param progress provided {@link Progress}.
	 */
	void save(Progress progress);
	
	/**
	 * Update {@link Progress} by provided parameters.
	 *
	 * @param id {@link Progress} ID.
	 * @param student {@link Student} entity.
	 * @param subject {@link Subject} entity.
	 * @param grade grade of the subject.
	 */
	void update(Integer id, Student student, Subject subject, Byte grade);
	
	/**
	 * Delete provided {@link Progress} entity.
	 *
	 * @param progress provided {@link Progress}.
	 */
	void delete(Progress progress);
}
