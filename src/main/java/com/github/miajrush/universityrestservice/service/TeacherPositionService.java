package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.TeacherPosition;
import com.github.miajrush.universityrestservice.repository.TeacherPositionRepository;
import com.github.miajrush.universityrestservice.web.controller.TeacherController;

import java.util.List;

/**
 * Used as a facade for a {@link TeacherController}. For more information on the methods, see {@link
 * TeacherPositionRepository}.
 */
public interface TeacherPositionService {
	/**
	 * Find all {@link TeacherPosition}s.
	 *
	 * @return the collection of the {@link TeacherPosition} objects.
	 */
	List<TeacherPosition> findAll();
	
	/**
	 * Find {@link TeacherPosition} by ID.
	 *
	 * @param id provided {@link TeacherPosition} id.
	 * @return {@link TeacherPosition} with provided ID or null otherwise.
	 */
	TeacherPosition findById(Integer id);
	
	/**
	 * Save provided {@link TeacherPosition} entity.
	 *
	 * @param teacherPosition provided {@link TeacherPosition}.
	 */
	void save(TeacherPosition teacherPosition);
	
	/**
	 * Update {@link TeacherPosition} by provided parameters.
	 *
	 * @param id {@link TeacherPosition} ID.
	 * @param name {@link TeacherPosition} name.
	 */
	void update(Integer id, String name);
	
	/**
	 * Delete provided {@link TeacherPosition} entity.
	 *
	 * @param teacherPosition provided {@link TeacherPosition}.
	 */
	void delete(TeacherPosition teacherPosition);
}
