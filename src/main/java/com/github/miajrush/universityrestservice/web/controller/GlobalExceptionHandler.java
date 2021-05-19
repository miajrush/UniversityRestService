package com.github.miajrush.universityrestservice.web.controller;

import com.github.miajrush.universityrestservice.web.exception.NoSuchEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller used to showcase what happens when an exception is thrown.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NoSuchEntityException.class)
	public ResponseEntity<String> handle(NoSuchEntityException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
