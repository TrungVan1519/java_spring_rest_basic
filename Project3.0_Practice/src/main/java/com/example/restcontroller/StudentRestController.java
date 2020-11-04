package com.example.restcontroller;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.exception.StudentNotFoundException;
import com.example.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	// autowire the StudentService 
	@Autowired
	private StudentService studentService;
	
	// endpoint for getting student list
	@GetMapping("/students")
	public List<Student> getStudents(){
		return studentService.getStudents();
	}
	
	// endpoint for getting a single student
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		Student student = studentService.getStudent(studentId);
		if(student == null) {
			throw new StudentNotFoundException("Student id not found: " + studentId);
		}
		
		return student;
	}
	
	// endpoint for adding a single student
	@PostMapping("/students")
	public Student saveStudent(@RequestBody Student student) {
		// @RequestBody means auto convert JSON to Java POJO for input
		// set id to 0 or null/empty means add new student instead of
		//		update student because we use saveOrUpdate() in 
		//		StudentDAOImpl instead of using save() 
		student.setId(0);
		
		// save new student in DB
		studentService.saveStudent(student);
		
		return student;
	}
	
	// endpoint for updating an existing student with unknown id
	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
		return student;
	}
	
	// endpoint for updating an existing student with identify id 
	@PutMapping("/students/{studentId}")
	public Student updateStudent(@RequestBody Student student,
								 @PathVariable int studentId) {
		Student existingStudent  = studentService.getStudent(studentId);
		if(existingStudent == null) {
			throw new StudentNotFoundException("Student id not found: " + studentId);
		}
		
		student.setId(studentId);
		studentService.saveStudent(student);
		
		return student;
	}
	
	
	// endpoint for deleing an existing Student
	@DeleteMapping("/students/{studentId}")
	public String deleteStudent(@PathVariable int studentId) {
		Student existingStudent = studentService.getStudent(studentId);
		if(existingStudent == null) {
			throw new StudentNotFoundException("Student id not found: " + studentId);
		}
		
		studentService.deleteStudent(studentId);
		return "Deleted student with id: " + studentId;
	}
}
