package com.github.miajrush.universityrestservice.web.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorUtils {
	/**
	 * @param bindingResult an object to represents binding results.
	 * @return a formatted error message.
	 */
	public static String getFormattedErrorMessage(BindingResult bindingResult) {
		StringBuilder message = new StringBuilder();
		List<FieldError> errors = bindingResult.getFieldErrors();
		
		for (FieldError error : errors) {
			message.append("\"").append(error.getField()).append("\" - ");
			message.append(error.getDefaultMessage()).append("\n");
		}
		return message.toString().trim();
	}
}
