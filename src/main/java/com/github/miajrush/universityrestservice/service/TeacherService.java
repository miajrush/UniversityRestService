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
	/**
	 * Find all {@link Teacher}s.
	 *
	 * @return the collection of the {@link Teacher} objects.
	 */
	List<Teacher> findAll();
	
	/**
	 * Find {@link Teacher} by ID.
	 *
	 * @param id provided {@link Teacher} id.
	 * @return {@link Teacher} with provided ID or null otherwise.
	 */
	Teacher findById(Integer id);
	
	/**
	 * Save provided {@link Teacher} entity.
	 *
	 * @param teacher provided {@link Teacher}.
	 */
	void save(Teacher teacher);
	
	/**
	 * Update {@link Teacher} by provided parameters.
	 *
	 * @param id {@link Teacher} ID.
	 * @param name {@link Teacher} name.
	 * @param phoneNumber {@link Teacher} phone number.
	 * @param position {@link TeacherPosition} entity.
	 */
	void update(Integer id, String name, String phoneNumber, TeacherPosition position);
	
	/**
	 * Delete provided {@link Teacher} entity.
	 *
	 * @param teacher provided {@link Teacher}.
	 */
	void delete(Teacher teacher);
}
