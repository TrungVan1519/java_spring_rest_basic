package com.example.restcontroller; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@GetMapping("/students")
	List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		for(int i = 1; i <= 3; i++) {
			students.add(new Student("firstName " + i, "lastName " + i));
		}
		
		return students;
	}
}
