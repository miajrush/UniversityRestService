package com.github.miajrush.universityrestservice.web.controller;

import com.github.miajrush.universityrestservice.model.Lesson;
import com.github.miajrush.universityrestservice.service.LessonService;
import com.github.miajrush.universityrestservice.service.LessonTypeService;
import com.github.miajrush.universityrestservice.service.SubjectService;
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
 * Controller used to manage {@link Lesson}s.
 */
@RestController
@RequestMapping("/api/lessons")
public class LessonController {
	private final LessonService lessonService;
	private final SubjectService subjectService;
	private final TeacherService teacherService;
	private final LessonTypeService lessonTypeService;
	
	@Autowired
	public LessonController(LessonService lessonService, SubjectService subjectService, TeacherService teacherService,
	                        LessonTypeService lessonTypeService) {
		this.lessonService = lessonService;
		this.subjectService = subjectService;
		this.teacherService = teacherService;
		this.lessonTypeService = lessonTypeService;
	}
	
	@GetMapping
	public List<Lesson> showAll() {
		return lessonService.findAll();
	}
	
	/**
	 * We need to find an object by ID to avoid exceptions.
	 *
	 * @param id a {@link Lesson} id
	 * @return the name of the HTML page
	 */
	@GetMapping("/{id}")
	public Lesson showById(@PathVariable Integer id) {
		return lessonService.findById(id);
	}
	
	/**
	 * If binding results has errors then an error message will be returned. We need to set the position field to avoid
	 * exceptions.<p>
	 *
	 * @param lesson the {@link Lesson} to save.
	 * @param bindingResult an object to represents binding results.
	 * @return the name of the HTML page.
	 */
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Lesson lesson, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		lesson.setId(null);
		setFields(lesson);
		lessonService.save(lesson);
		return new ResponseEntity<>(lesson, HttpStatus.OK);
	}
	
	/**
	 * If the binding result has errors then an error message will be returned. We need to set fields to avoid
	 * exceptions.<p>
	 *
	 * @param id the {@link Lesson} id.
	 * @param lesson the {@link Lesson} to update.
	 * @param bindingResult an object to represents binding results.
	 * @return {@link ResponseEntity} with a result.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Lesson lesson,
	                                BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		lesson.setId(id);
		setFields(lesson);
		lessonService.update(lesson.getId(), lesson.getSubject(), lesson.getTeacher(), lesson.getLessonType());
		return new ResponseEntity<>(lesson, HttpStatus.OK);
	}
	
	/**
	 * We need to find an object by ID to avoid exceptions.
	 *
	 * @param id a {@link Lesson} id to delete
	 * @return the name of the HTML page
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		lessonService.delete(lessonService.findById(id));
		return new ResponseEntity<>("Lesson with ID = " + id + " was deleted.", HttpStatus.OK);
	}
	
	/*
	 * We need to find objects by ID and set them to avoid exceptions.
	 */
	private void setFields(Lesson lesson) {
		lesson.setSubject(subjectService.findById(lesson.getSubject().getId()));
		lesson.setTeacher(teacherService.findById(lesson.getTeacher().getId()));
		lesson.setLessonType(lessonTypeService.findById(lesson.getLessonType().getId()));
	}
}
