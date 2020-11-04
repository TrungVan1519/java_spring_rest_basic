package com.example.sample_full.main;

import java.io.File;

import com.example.sample_full.pojo.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	public static void main(String[] args) {
		try {
			
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// read JSON file and convert to Java POJO
			Student student = mapper.readValue(
					new File("json_data/sample-full.json"), Student.class);
			
			// sout Java POJO
			System.out.println(student);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
