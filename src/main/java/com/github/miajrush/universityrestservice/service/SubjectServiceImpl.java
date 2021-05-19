package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Subject;
import com.github.miajrush.universityrestservice.repository.SubjectRepository;
import com.github.miajrush.universityrestservice.web.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link SubjectService} interface and a placeholder for @Transactional annotations.
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	private final SubjectRepository subjectRepository;
	
	@Autowired
	public SubjectServiceImpl(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	@Override
	public List<Subject> findAll() {
		return subjectRepository.findAll();
	}
	
	@Override
	public Subject findById(Integer id) {
		Optional<Subject> subject = subjectRepository.findById(id);
		if (subject.isPresent()) {
			return subject.get();
		} else {
			throw new NoSuchEntityException(Subject.class, id);
		}
	}
	
	@Override
	public void save(Subject subject) {
		subjectRepository.save(subject);
	}
	
	@Override
	public void update(Integer id, String name, Integer hoursNumber) {
		subjectRepository.update(id, name, hoursNumber);
	}
	
	@Override
	public void delete(Subject subject) {
		subjectRepository.delete(subject);
	}
}
