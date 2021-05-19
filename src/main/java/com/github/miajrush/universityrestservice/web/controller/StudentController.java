package com.github.miajrush.universityrestservice.web.controller;

import com.github.miajrush.universityrestservice.model.Group;
import com.github.miajrush.universityrestservice.model.Progress;
import com.github.miajrush.universityrestservice.model.Student;
import com.github.miajrush.universityrestservice.service.GroupService;
import com.github.miajrush.universityrestservice.service.ProgressService;
import com.github.miajrush.universityrestservice.service.StudentService;
import com.github.miajrush.universityrestservice.util.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	private final StudentService studentService;
	private final GroupService groupService;
	private final ProgressService progressService;
	private Group oldGroup;
	
	@Autowired
	public StudentController(StudentService studentService, GroupService groupService,
	                         ProgressService progressService) {
		this.studentService = studentService;
		this.groupService = groupService;
		this.progressService = progressService;
		
		oldGroup = null;
	}
	
	@GetMapping
	public List<Student> showAll() {
		return studentService.findAll();
	}
	
	@GetMapping("/{id}")
	public Student showById(@PathVariable Integer id) {
		return studentService.findById(id);
	}
	
	/**
	 * If binding results has errors then an error message will be returned. We need to set the position field to avoid
	 * exceptions.<p>
	 *
	 * @param student the {@link Student} to save
	 * @param bindingResult an object to represents binding results
	 * @return the name of the HTML page
	 */
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		student.setId(null);
		student.setGroup(groupService.findById(student.getGroup().getId()));
		studentService.save(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		student.setId(id);
		Student oldStudent = studentService.findById(id);
		student.setGroup(groupService.findById(student.getGroup().getId()));
		studentService.update(student.getId(), student.getName(), student.getCourse(), student.getPhoneNumber(),
		                      student.getEmail(), student.getGroup(), oldStudent.getGroup());
		
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Student student = studentService.findById(id);
		List<Progress> progress = progressService.findAll();
		
		for (Progress p : progress) {
			if (p.getStudent().equals(student)) {
				progressService.delete(p);
			}
		}
		
		studentService.delete(student);
		return new ResponseEntity<>("Student with ID = " + id + " was deleted.", HttpStatus.OK);
	}
}
