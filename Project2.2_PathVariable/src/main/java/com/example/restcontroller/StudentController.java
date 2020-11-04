package com.example.restcontroller; 

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	private List<Student> students;
	
	@PostConstruct
	private void loadData() {
		students = new ArrayList<Student>();
		for(int i = 1; i <= 3; i++) {
			students.add(new Student("firstName " + i, "lastName " + i));
		}
	}
	
	@GetMapping("/students")
	private List<Student> getStudents() {
		return students;
	}
	
	@GetMapping("/students/{studentId}")
	private Student getStudent(@PathVariable int studentId) {
		return students.get(studentId);
	}
}
