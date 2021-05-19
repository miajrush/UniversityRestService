package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.LessonType;
import com.github.miajrush.universityrestservice.repository.LessonTypeRepository;
import com.github.miajrush.universityrestservice.web.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link LessonTypeService} interface and a placeholder for @Transactional annotations.
 */
@Service
@Transactional
public class LessonTypeServiceImpl implements LessonTypeService {
	private final LessonTypeRepository lessonTypeRepository;
	
	@Autowired
	public LessonTypeServiceImpl(LessonTypeRepository lessonTypeRepository) {
		this.lessonTypeRepository = lessonTypeRepository;
	}
	
	@Override
	public List<LessonType> findAll() {
		return lessonTypeRepository.findAll();
	}
	
	@Override
	public LessonType findById(Integer id) {
		Optional<LessonType> lesson = lessonTypeRepository.findById(id);
		if (lesson.isPresent()) {
			return lesson.get();
		} else {
			throw new NoSuchEntityException(LessonType.class, id);
		}
	}
	
	@Override
	public void save(LessonType lessonType) {
		lessonTypeRepository.save(lessonType);
	}
	
	@Override
	public void update(Integer id, String name) {
		lessonTypeRepository.update(id, name);
	}
	
	@Override
	public void delete(LessonType lessonType) {
		lessonTypeRepository.delete(lessonType);
	}
}
