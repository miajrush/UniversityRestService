package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Lesson;
import com.github.miajrush.universityrestservice.model.Lesson;
import com.github.miajrush.universityrestservice.model.LessonType;
import com.github.miajrush.universityrestservice.model.Subject;
import com.github.miajrush.universityrestservice.model.Teacher;
import com.github.miajrush.universityrestservice.repository.LessonRepository;
import com.github.miajrush.universityrestservice.web.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link LessonService} interface and a placeholder for @Transactional annotations.
 */
@Service
@Transactional
public class LessonServiceImpl implements LessonService {
	private final LessonRepository lessonRepository;
	
	@Autowired
	public LessonServiceImpl(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}
	
	@Override
	public List<Lesson> findAll() {
		return lessonRepository.findAll();
	}
	
	@Override
	public Lesson findById(Integer id) {
		Optional<Lesson> lesson = lessonRepository.findById(id);
		if (lesson.isPresent()) {
			return lesson.get();
		} else {
			throw new NoSuchEntityException(Lesson.class, id);
		}
	}
	
	@Override
	public void save(Lesson lesson) {
		lessonRepository.save(lesson);
	}
	
	@Override
	public void update(Integer id, Subject subject, Teacher teacher, LessonType lessonType) {
		lessonRepository.update(id, subject, teacher, lessonType);
	}
	
	@Override
	public void delete(Lesson lesson) {
		lessonRepository.delete(lesson);
	}
}
