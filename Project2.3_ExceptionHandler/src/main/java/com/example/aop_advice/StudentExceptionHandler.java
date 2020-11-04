package com.example.aop_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.entity.StudentException;
import com.example.exception.StudentNotFoundException;

@ControllerAdvice
public class StudentExceptionHandler {

	// define endpoint for exception handler - catch error 
	//		"List index out of bound"
	@ExceptionHandler
	private ResponseEntity<StudentException> handleException(StudentNotFoundException e){
		
		StudentException studentException = new StudentException();
		studentException.setStatus(HttpStatus.NOT_FOUND.value());
		studentException.setMessage(e.getMessage());
		studentException.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<StudentException>(studentException, HttpStatus.NOT_FOUND);
	}
	
	// define endpoint for exception handler - catch any error else
	@ExceptionHandler
	private ResponseEntity<StudentException> handleException(Exception e){
		
		StudentException studentException = new StudentException();
		studentException.setStatus(HttpStatus.BAD_REQUEST.value());
		studentException.setMessage(":V said: " + e.getMessage());
		studentException.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<StudentException>(studentException, HttpStatus.BAD_REQUEST);
	}
}
