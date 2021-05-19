package com.github.miajrush.universityrestservice.web.controller;

import com.github.miajrush.universityrestservice.model.Group;
import com.github.miajrush.universityrestservice.service.GroupService;
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
@RequestMapping("/api/groups")
public class GroupController {
	private final GroupService groupService;
	
	@Autowired
	public GroupController(GroupService groupService) {
		this.groupService = groupService;
	}
	
	@GetMapping
	public List<Group> showAll() {
		return groupService.findAll();
	}
	
	@GetMapping("/{id}")
	public Group showById(@PathVariable Integer id) {
		return groupService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Group group, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		group.setStudentsNumber(0);
		group.setId(null);
		groupService.save(group);
		return new ResponseEntity<>(group, HttpStatus.OK);
	}
	
	/**
	 * If the binding result has errors then an error message will be returned. We need to set the id field to avoid
	 * exceptions.<p>
	 *
	 * @param id the {@link Group} id.
	 * @param group the {@link Group} to update.
	 * @param bindingResult an object to represents binding results.
	 * @return {@link ResponseEntity} with a result.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Group group,
	                                BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorUtils.getFormattedErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
		}
		
		if (group.getStudentsNumber() != group.getStudents().size()) {
			group.setStudentsNumber(group.getStudents().size());
		}
		
		group.setId(id);
		groupService.update(group.getId(), group.getName(), group.getStudentsNumber());
		return new ResponseEntity<>(group, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Group group = groupService.findById(id);
		if (group.getStudentsNumber() > 0) {
			return new ResponseEntity<>("First, remove students from the group", HttpStatus.BAD_REQUEST);
		}
		
		groupService.delete(groupService.findById(id));
		return new ResponseEntity<>("Group with ID = " + id + " was deleted.", HttpStatus.OK);
	}
}
