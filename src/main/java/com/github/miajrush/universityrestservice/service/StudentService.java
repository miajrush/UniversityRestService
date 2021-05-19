package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Group;
import com.github.miajrush.universityrestservice.model.Student;
import com.github.miajrush.universityrestservice.repository.StudentRepository;
import com.github.miajrush.universityrestservice.web.controller.GroupController;
import com.github.miajrush.universityrestservice.web.controller.ProgressController;
import com.github.miajrush.universityrestservice.web.controller.StudentController;

import java.util.List;

/**
 * Used as a facade for {@link StudentController}, {@link GroupController} and {@link ProgressController}. For more
 * information on the methods, see {@link StudentRepository}.
 */
public interface StudentService {
	/**
	 * Find all {@link Student}s.
	 *
	 * @return the collection of the {@link Student} objects.
	 */
	List<Student> findAll();
	
	/**
	 * Find {@link Student} by ID.
	 *
	 * @param id provided {@link Student} id.
	 * @return {@link Student} with provided ID or null otherwise.
	 */
	Student findById(Integer id);
	
	/**
	 * Save provided {@link Student} entity.
	 *
	 * @param student provided {@link Student}.
	 */
	void save(Student student);
	
	/**
	 * Update {@link Student} by provided parameters.
	 *
	 * @param id {@link Student} ID.
	 * @param name {@link Student} name.
	 * @param course {@link Student} course.
	 * @param phoneNumber {@link Student} phoneNumber.
	 * @param email {@link Student} email.
	 * @param group {@link Student} group.
	 * @param oldGroup {@link Student} old group.
	 */
	void update(Integer id, String name, Integer course, String phoneNumber, String email, Group group,
	            Group oldGroup);
	
	/**
	 * Delete provided {@link Student} entity.
	 *
	 * @param student provided {@link Student}.
	 */
	void delete(Student student);
}
