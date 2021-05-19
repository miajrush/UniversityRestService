package com.github.miajrush.universityrestservice.service;

import com.github.miajrush.universityrestservice.model.Group;
import com.github.miajrush.universityrestservice.model.Student;
import com.github.miajrush.universityrestservice.repository.StudentRepository;
import com.github.miajrush.universityrestservice.web.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link StudentService} interface and a placeholder for @Transactional annotations.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	private final StudentRepository studentRepository;
	private final GroupService groupService;
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, GroupService groupService) {
		this.studentRepository = studentRepository;
		this.groupService = groupService;
	}
	
	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}
	
	@Override
	public Student findById(Integer id) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			return student.get();
		} else {
			throw new NoSuchEntityException(Student.class, id);
		}
	}
	
	/**
	 * We need to add the student to students list and then save the student.
	 *
	 * @param student the {@link Student} to save
	 */
	@Override
	public void save(Student student) {
		student.getGroup().addStudent(student);
		studentRepository.save(student);
	}
	
	/**
	 * We need to transfer the student to another group.
	 *
	 * @param oldGroup the {@link Group} that stores data about old group.
	 */
	@Override
	public void update(Integer id, String name, Integer course, String phoneNumber, String email, Group group,
	                   Group oldGroup) {
		if (!oldGroup.equals(group)) {
			Student tmp = new Student(id, name, course, phoneNumber, email, group);
			oldGroup.removeStudent(tmp);
			group.addStudent(tmp);
			groupService.update(oldGroup.getId(), oldGroup.getName(), oldGroup.getStudentsNumber());
		}
		
		groupService.update(group.getId(), group.getName(), group.getStudentsNumber());
		studentRepository.update(id, name, course, phoneNumber, email, group);
	}
	
	/**
	 * We need to remove the student from students list and then delete the student.
	 *
	 * @param student the {@link Student} to delete
	 */
	@Override
	public void delete(Student student) {
		student.getGroup().removeStudent(student);
		studentRepository.delete(student);
	}
}
