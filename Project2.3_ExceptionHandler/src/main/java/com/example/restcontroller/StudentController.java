package com.example.restcontroller; 

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.exception.StudentNotFoundException;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	private List<Student> students;
	
	// define @PostContructor to load the student data only once!!!
	@PostConstruct
	private void loadData() {
		students = new ArrayList<Student>();
		for(int i = 1; i <= 3; i++) {
			students.add(new Student("firstName " + i, "lastName " + i));
		}
	}
	
	// define endpoint for "/students" - return list of students
	@GetMapping("/students")
	private List<Student> getStudents() {
		return students;
	}
	
	// define endpoint for "/students/{studentId}" - return single student 
	//		at index
	@GetMapping("/students/{studentId}")
	private Student getStudent(@PathVariable int studentId) {
		if(studentId < 0 || studentId >= students.size()) {
			throw new StudentNotFoundException("studentId is not found: " + studentId);
		}
		
		return students.get(studentId);
	}
}
