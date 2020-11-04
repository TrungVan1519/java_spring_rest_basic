package com.example.sample_lite.main;

import java.io.File;

import com.example.sample_lite.pojo.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	public static void main(String[] args) {
		try {
			
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file and convert to Java POJO
			Student student = mapper.readValue(
					new File("json_data/sample-lite.json"), Student.class);
			
			// sout Java POJO
			System.out.println(student);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
