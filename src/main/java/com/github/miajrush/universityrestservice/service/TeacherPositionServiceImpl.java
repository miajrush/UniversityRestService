package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.TeacherPosition;
import com.github.miajrush.universityrestservice.repository.TeacherPositionRepository;
import com.github.miajrush.universityrestservice.web.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link TeacherPositionService} interface and a placeholder for @Transactional annotations.
 */
@Service
@Transactional
public class TeacherPositionServiceImpl implements TeacherPositionService {
	private final TeacherPositionRepository teacherPositionRepository;
	
	@Autowired
	public TeacherPositionServiceImpl(TeacherPositionRepository teacherPositionRepository) {
		this.teacherPositionRepository = teacherPositionRepository;
	}
	
	@Override
	public List<TeacherPosition> findAll() {
		return teacherPositionRepository.findAll();
	}
	
	@Override
	public TeacherPosition findById(Integer id) {
		Optional<TeacherPosition> teacherPosition = teacherPositionRepository.findById(id);
		if (teacherPosition.isPresent()) {
			return teacherPosition.get();
		} else {
			throw new NoSuchEntityException(TeacherPosition.class, id);
		}
	}
	
	@Override
	public void save(TeacherPosition teacherPosition) {
		teacherPositionRepository.save(teacherPosition);
	}
	
	@Override
	public void update(Integer id, String name) {
		teacherPositionRepository.update(id, name);
	}
	
	@Override
	public void delete(TeacherPosition teacherPosition) {
		teacherPositionRepository.delete(teacherPosition);
	}
}
