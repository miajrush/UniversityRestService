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
	/**
	 * Find all {@link Group}s.
	 *
	 * @return the collection of the {@link Group} objects.
	 */
	List<Group> findAll();
	
	/**
	 * Find {@link Group} by ID.
	 *
	 * @param id provided {@link Group} id.
	 * @return {@link Group} with provided ID or null otherwise.
	 */
	Group findById(Integer id);
	
	/**
	 * Save provided {@link Group} entity.
	 *
	 * @param group provided {@link Group}.
	 */
	void save(Group group);
	
	/**
	 * Update {@link Group} by provided parameters.
	 *
	 * @param id {@link Group} ID.
	 * @param name {@link Group} name.
	 * @param studentsNumber the number of students in the {@link Group}.
	 */
	void update(Integer id, String name, Integer studentsNumber);
	
	/**
	 * Delete provided {@link Group} entity.
	 *
	 * @param group provided {@link Group}.
	 */
	void delete(Group group);
}
