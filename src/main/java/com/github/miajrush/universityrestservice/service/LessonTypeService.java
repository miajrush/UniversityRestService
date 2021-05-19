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
	/**
	 * Find all {@link LessonType}s.
	 *
	 * @return the collection of the {@link LessonType} objects.
	 */
	List<LessonType> findAll();
	
	/**
	 * Find {@link LessonType} by ID.
	 *
	 * @param id provided {@link LessonType} id.
	 * @return {@link LessonType} with provided ID or null otherwise.
	 */
	LessonType findById(Integer id);
	
	/**
	 * Save provided {@link LessonType} entity.
	 *
	 * @param lessonType provided {@link LessonType}.
	 */
	void save(LessonType lessonType);
	
	/**
	 * Update {@link LessonType} by provided parameters.
	 *
	 * @param id {@link LessonType} ID.
	 * @param name {@link LessonType} name.
	 */
	void update(Integer id, String name);
	
	/**
	 * Delete provided {@link LessonType} entity.
	 *
	 * @param lessonType provided {@link LessonType}.
	 */
	void delete(LessonType lessonType);
}
