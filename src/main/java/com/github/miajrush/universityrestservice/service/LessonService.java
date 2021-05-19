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
	List<Lesson> findAll();
	
	Lesson findById(Integer id);
	
	void save(Lesson lesson);
	
	void update(Integer id, Subject subject, Teacher teacher, LessonType lessonType);
	
	void delete(Lesson lesson);
}
