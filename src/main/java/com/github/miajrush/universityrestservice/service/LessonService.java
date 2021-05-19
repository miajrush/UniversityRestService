package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Lesson;
import com.github.miajrush.universityrestservice.model.LessonType;
import com.github.miajrush.universityrestservice.model.Subject;
import com.github.miajrush.universityrestservice.model.Teacher;
import com.github.miajrush.universityrestservice.repository.LessonRepository;
import com.github.miajrush.universityrestservice.web.controller.LessonController;
import com.github.miajrush.universityrestservice.web.controller.SubjectController;
import com.github.miajrush.universityrestservice.web.controller.TeacherController;

import java.util.List;

/**
 * Used as a facade for {@link LessonController}, {@link SubjectController} and {@link TeacherController}. For more
 * information on the methods, see {@link LessonRepository}.
 */
public interface LessonService {
	/**
	 * Find all {@link Lesson}s.
	 *
	 * @return the collection of the {@link Lesson} objects.
	 */
	List<Lesson> findAll();
	
	/**
	 * Find {@link Lesson} by ID.
	 *
	 * @param id provided {@link Lesson} id.
	 * @return {@link Lesson} with provided ID or null otherwise.
	 */
	Lesson findById(Integer id);
	
	/**
	 * Save provided {@link Lesson} entity.
	 *
	 * @param lesson provided {@link Lesson}.
	 */
	void save(Lesson lesson);
	
	/**
	 * Update {@link Lesson} by provided parameters.
	 *
	 * @param id {@link Lesson} ID.
	 * @param subject {@link Subject} entity.
	 * @param teacher {@link Teacher} entity.
	 * @param lessonType {@link LessonType} entity.
	 */
	void update(Integer id, Subject subject, Teacher teacher, LessonType lessonType);
	
	/**
	 * Delete provided {@link Lesson} entity.
	 *
	 * @param lesson provided {@link Lesson}.
	 */
	void delete(Lesson lesson);
}
