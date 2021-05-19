package com.github.miajrush.universityrestservice.web.controller;

import com.github.miajrush.universityrestservice.model.Lesson;
import com.github.miajrush.universityrestservice.model.Progress;
import com.github.miajrush.universityrestservice.model.Subject;
import com.github.miajrush.universityrestservice.service.LessonService;
import com.github.miajrush.universityrestservice.service.ProgressService;
import com.github.miajrush.universityrestservice.service.SubjectService;
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

/**
 * Controller used to manage {@link Subject}s.
 */
@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
	private final SubjectService subjectService;
	private final LessonService lessonService;
	private final ProgressService progressService;
	
	@Autowired
	public SubjectController(SubjectService subjectService, LessonService lessonService,
	                         ProgressService progressService) {
		this.subjectService = subjectService;
		this.lessonService = lessonService;
		this.progressService = progressService;
	}
	
	@GetMapping
	public List<Subject> showAll() {
		return subjectService.findAll();
	}
	
	/**
	 * We need to find an object by ID to avoid exceptions.
	 *
	 * @param id a {@link Subject} id
	 * @return the name of the HTML page
	 */
	@GetMapping("/{id}")
	public Subject showById(@PathVariable Integer id) {
		return subjectService.findById(id);
	}
	
	/**
	 * If binding results has errors then an error message will be returned. We need to set the position field to avoid
	 * exceptions.<p>
	 *
	 * @param subject the {@link Subject} to save
	 * @param bindingResult an object to represents binding results
	 * @return the name of the HTML page
	 */
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Subject subject, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		subject.setId(null);
		subjectService.save(subject);
		return new ResponseEntity<>(subject, HttpStatus.OK);
	}
	
	/**
	 * If the binding result has errors then an error message will be returned. We need to set the id field to avoid
	 * exceptions.<p>
	 *
	 * @param id the {@link Subject} id.
	 * @param subject the {@link Subject} to update.
	 * @param bindingResult an object to represents binding results.
	 * @return {@link ResponseEntity} with a result.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Subject subject,
	                                BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		subject.setId(id);
		subjectService.update(subject.getId(), subject.getName(), subject.getHoursNumber());
		return new ResponseEntity<>(subject, HttpStatus.OK);
	}
	
	/**
	 * If the list of lessons contains the subject, the model will add an "error" attribute and the page will update.
	 * <p>
	 * If the list of progress contains the subject, the progress will be deleted.
	 *
	 * @param id the {@link Subject} id
	 * @return the name of the HTML page
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Subject subject = subjectService.findById(id);
		List<Lesson> lessons = lessonService.findAll();
		for (Lesson lesson : lessons) {
			if (lesson.getSubject().equals(subject)) {
				return new ResponseEntity<>("First, remove subject from the lessons", HttpStatus.BAD_REQUEST);
			}
		}
		
		List<Progress> progresses = progressService.findAll();
		for (Progress progress : progresses) {
			if (progress.getSubject().equals(subject)) {
				progressService.delete(progress);
				break;
			}
		}
		
		subjectService.delete(subject);
		return new ResponseEntity<>("Subject with ID = " + subject.getId() + " was deleted.", HttpStatus.OK);
	}
}
