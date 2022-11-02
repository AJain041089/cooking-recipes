package com.recipemanager.cookingrecipe.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorDTO {

	/** The status. */
	private final HttpStatus status;

	/** The message. */
	private final String message;

	/** The errors. */
	private final List<String> errors;

	/**
	 * Instantiates a new error DTO.
	 *
	 * @param status  the status
	 * @param message the message
	 * @param errors  the errors
	 */
	public ErrorDTO(HttpStatus status, String message, String errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(errors);
	}

}
