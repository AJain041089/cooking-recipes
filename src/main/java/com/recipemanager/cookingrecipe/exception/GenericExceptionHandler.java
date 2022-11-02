package com.recipemanager.cookingrecipe.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.recipemanager.cookingrecipe.model.ErrorDTO;

/**
 * The Class GenericExceptionHandler.
 */
@ControllerAdvice
public class GenericExceptionHandler {

	/**
	 * Handle exception.
	 *
	 * @param exception the exception
	 * @return the response entity
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleException(ResourceNotFoundException exception) {
		ErrorDTO dto = new ErrorDTO(HttpStatus.NOT_FOUND, exception.toString(), exception.toString());
		return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception) {
		ErrorDTO dto = new ErrorDTO(HttpStatus.BAD_REQUEST, "Validation Failed : Missing Field", exception.toString());
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<Object> handleTransactionSystemException(TransactionSystemException exception) {
		ErrorDTO dto = new ErrorDTO(HttpStatus.BAD_REQUEST, "Transcation rollback  : Due to Missing Field",
				exception.toString());
		return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
	}
}
