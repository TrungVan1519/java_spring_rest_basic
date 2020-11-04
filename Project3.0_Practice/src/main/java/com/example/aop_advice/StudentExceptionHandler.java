package com.example.aop_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.entity.StudentException;
import com.example.exception.StudentNotFoundException;

@ControllerAdvice
public class StudentExceptionHandler {

	// endpoint for bad studentId
	@ExceptionHandler
	public ResponseEntity<StudentException> handleException(StudentNotFoundException e){
		StudentException studentException = new StudentException(
					HttpStatus.NOT_FOUND.value(),
					e.getMessage(),
					System.currentTimeMillis()
		);
		return new ResponseEntity<StudentException>(studentException, HttpStatus.NOT_FOUND);
	}
	
	// endpoint for any exception else
	@ExceptionHandler
	public ResponseEntity<StudentException> handleException(Exception e){
		StudentException studentException = new StudentException(
					HttpStatus.BAD_REQUEST.value(),
					"Exception said :v :" + e.getMessage(),
					System.currentTimeMillis()
		);
		return new ResponseEntity<StudentException>(studentException, HttpStatus.BAD_REQUEST);
	}
}
