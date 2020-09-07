package edu.spring.brewery.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> handleValidationException(ConstraintViolationException cve)
	{
		List<String> exceptions = cve.getConstraintViolations().stream().map(cv -> {
			return cv.getPropertyPath() +" : "+ cv.getMessage();
		}).collect(Collectors.toList());
		return new ResponseEntity<List>(exceptions, HttpStatus.BAD_REQUEST);
	}
}
