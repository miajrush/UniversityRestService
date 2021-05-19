package com.github.miajrush.universityrestservice.web.controller;

import com.github.miajrush.universityrestservice.model.Progress;
import com.github.miajrush.universityrestservice.service.ProgressService;
import com.github.miajrush.universityrestservice.service.StudentService;
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

@RestController
@RequestMapping("/api/progress")
public class ProgressController {
	private final ProgressService progressService;
	private final StudentService studentService;
	private final SubjectService subjectService;
	
	@Autowired
	public ProgressController(ProgressService progressService, StudentService studentService,
	                          SubjectService subjectService) {
		this.progressService = progressService;
		this.studentService = studentService;
		this.subjectService = subjectService;
	}
	
	@GetMapping
	public List<Progress> showAll() {
		return progressService.findAll();
	}
	
	@GetMapping("/{id}")
	public Progress showById(@PathVariable Integer id) {
		return progressService.findById(id);
	}
	
	/**
	 * If binding results has errors then an error message will be returned. We need to set the position field to avoid
	 * exceptions.<p>
	 *
	 * @param progress the {@link Progress} to save
	 * @param bindingResult an object to represents binding results
	 * @return the name of the HTML page
	 */
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Progress progress, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		progress.setId(null);
		setFields(progress);
		progressService.save(progress);
		return new ResponseEntity<>(progress, HttpStatus.OK);
	}
	
	/**
	 * If the binding result has errors then an error message will be returned. We need to set fields to avoid
	 * exceptions.<p>
	 *
	 * @param id the {@link Progress} id.
	 * @param progress the {@link Progress} to update.
	 * @param bindingResult an object to represents binding results.
	 * @return {@link ResponseEntity} with a result.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Progress progress,
	                                BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		progress.setId(id);
		setFields(progress);
		progressService.update(progress.getId(), progress.getStudent(), progress.getSubject(), progress.getGrade());
		return new ResponseEntity<>(progress, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		progressService.delete(progressService.findById(id));
		return new ResponseEntity<>("Progress with ID = " + id + " was deleted.", HttpStatus.OK);
	}
	
	/*
	 * We need to find objects by ID and set them to output the result without null objects.
	 */
	private void setFields(Progress progress) {
		progress.setStudent(studentService.findById(progress.getStudent().getId()));
		progress.setSubject(subjectService.findById(progress.getSubject().getId()));
	}
}
