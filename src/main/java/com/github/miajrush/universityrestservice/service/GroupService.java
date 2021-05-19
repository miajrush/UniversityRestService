package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Group;
import com.github.miajrush.universityrestservice.repository.GroupRepository;
import com.github.miajrush.universityrestservice.web.controller.GroupController;
import com.github.miajrush.universityrestservice.web.controller.StudentController;

import java.util.List;

/**
 * Used as a facade for {@link GroupController} and {@link StudentController}. For more information on the methods, see
 * {@link GroupRepository}.
 */
public interface GroupService {
	List<Group> findAll();
	
	Group findById(Integer id);
	
	void save(Group group);
	
	void update(Integer id, String name, Integer studentsNumber);
	
	void delete(Group group);
}
