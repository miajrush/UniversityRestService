package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Group;
import com.github.miajrush.universityrestservice.repository.GroupRepository;
import com.github.miajrush.universityrestservice.web.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link GroupService} interface and a placeholder for @Transactional annotations.
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {
	private final GroupRepository groupRepository;
	
	@Autowired
	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}
	
	@Override
	public List<Group> findAll() {
		return groupRepository.findAll();
	}
	
	@Override
	public Group findById(Integer id) {
		Optional<Group> group = groupRepository.findById(id);
		if (group.isPresent()) {
			return group.get();
		} else {
			throw new NoSuchEntityException(Group.class, id);
		}
	}
	
	@Override
	public void save(Group group) {
		groupRepository.save(group);
	}
	
	@Override
	public void update(Integer id, String name, Integer studentsNumber) {
		groupRepository.update(id, name, studentsNumber);
	}
	
	@Override
	public void delete(Group group) {
		groupRepository.delete(group);
	}
}
