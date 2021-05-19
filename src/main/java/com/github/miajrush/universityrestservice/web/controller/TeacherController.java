package com.github.miajrush.universityrestservice.web.controller;

import com.github.miajrush.universityrestservice.model.Lesson;
import com.github.miajrush.universityrestservice.model.Teacher;
import com.github.miajrush.universityrestservice.service.LessonService;
import com.github.miajrush.universityrestservice.service.TeacherPositionService;
import com.github.miajrush.universityrestservice.service.TeacherService;
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

/**
 * Controller used to manage {@link Teacher}s.
 */
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	private final TeacherService teacherService;
	private final TeacherPositionService teacherPositionService;
	private final LessonService lessonService;
	
	@Autowired
	public TeacherController(TeacherService teacherService, TeacherPositionService teacherPositionService,
	                         LessonService lessonService) {
		this.teacherService = teacherService;
		this.teacherPositionService = teacherPositionService;
		this.lessonService = lessonService;
	}
	
	@GetMapping
	public List<Teacher> showAll() {
		return teacherService.findAll();
	}
	
	/**
	 * We need to find an object by ID to avoid exceptions.
	 *
	 * @param id a {@link Teacher} id
	 * @return {@link ResponseEntity} with a result.
	 */
	@GetMapping("/{id}")
	public Teacher showById(@PathVariable Integer id) {
		return teacherService.findById(id);
	}
	
	/**
	 * If binding results has errors then an error message will be returned. We need to set the position field to avoid
	 * exceptions.<p>
	 *
	 * @param teacher the {@link Teacher} to save
	 * @param bindingResult an object to represents binding results
	 * @return {@link ResponseEntity} with a result.
	 */
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Teacher teacher, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		teacher.setId(null);
		teacher.setPosition(teacherPositionService.findById(teacher.getPosition().getId()));
		teacherService.save(teacher);
		return new ResponseEntity<>(teacher, HttpStatus.OK);
	}
	
	/**
	 * If the binding result has errors then an error message will be returned. We need to set the position and the id
	 * field to avoid exceptions.<p>
	 *
	 * @param id the {@link Teacher} id.
	 * @param teacher the {@link Teacher} to update.
	 * @param bindingResult an object to represents binding results.
	 * @return {@link ResponseEntity} with a result.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Teacher teacher,
	                                BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		teacher.setId(id);
		teacher.setPosition(teacherPositionService.findById(teacher.getPosition().getId()));
		teacherService.update(teacher.getId(), teacher.getName(), teacher.getPhoneNumber(), teacher.getPosition());
		return new ResponseEntity<>(teacher, HttpStatus.OK);
	}
	
	/**
	 * If the list of lessons contains the teacher, an error message will be returned.
	 *
	 * @param id the {@link Teacher} ID
	 * @return {@link ResponseEntity} with a result.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Teacher teacher = teacherService.findById(id);
		List<Lesson> lessons = lessonService.findAll();
		
		for (Lesson lesson : lessons) {
			if (lesson.getTeacher().equals(teacher)) {
				return new ResponseEntity<>("First, remove teacher from the lessons", HttpStatus.BAD_REQUEST);
			}
		}
		
		teacherService.delete(teacher);
		return new ResponseEntity<>("Teacher with ID = " + teacher.getId() + " was deleted.", HttpStatus.OK);
	}
}
