package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Progress;
import com.github.miajrush.universityrestservice.model.Student;
import com.github.miajrush.universityrestservice.model.Subject;
import com.github.miajrush.universityrestservice.repository.ProgressRepository;
import com.github.miajrush.universityrestservice.web.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link ProgressService} interface and a placeholder for @Transactional annotations.
 */
@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {
	private final ProgressRepository progressRepository;
	
	@Autowired
	public ProgressServiceImpl(ProgressRepository progressRepository) {
		this.progressRepository = progressRepository;
	}
	
	@Override
	public List<Progress> findAll() {
		return progressRepository.findAll();
	}
	
	@Override
	public Progress findById(Integer id) {
		Optional<Progress> progress = progressRepository.findById(id);
		if (progress.isPresent()) {
			return progress.get();
		} else {
			throw new NoSuchEntityException(Progress.class, id);
		}
	}
	
	@Override
	public void save(Progress progress) {
		progressRepository.save(progress);
	}
	
	@Override
	public void update(Integer id, Student student, Subject subject, Byte grade) {
		progressRepository.update(id, student, subject, grade);
	}
	
	@Override
	public void delete(Progress progress) {
		progressRepository.delete(progress);
	}
}
