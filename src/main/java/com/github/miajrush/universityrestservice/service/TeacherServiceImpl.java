package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Teacher;
import com.github.miajrush.universityrestservice.model.TeacherPosition;
import com.github.miajrush.universityrestservice.repository.TeacherRepository;
import com.github.miajrush.universityrestservice.web.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link TeacherService} interface and a placeholder for @Transactional annotations.
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
	private final TeacherRepository teacherRepository;
	
	@Autowired
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}
	
	@Override
	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}
	
	@Override
	public Teacher findById(Integer id) {
		Optional<Teacher> subject = teacherRepository.findById(id);
		if (subject.isPresent()) {
			return subject.get();
		} else {
			throw new NoSuchEntityException(Teacher.class, id);
		}
	}
	
	@Override
	public void save(Teacher teacher) {
		teacherRepository.save(teacher);
	}
	
	@Override
	public void update(Integer id, String name, String phoneNumber, TeacherPosition position) {
		teacherRepository.update(id, name, phoneNumber, position);
	}
	
	@Override
	public void delete(Teacher teacher) {
		teacherRepository.delete(teacher);
	}
}
