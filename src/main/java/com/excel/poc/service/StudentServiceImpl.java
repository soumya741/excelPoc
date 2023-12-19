package com.excel.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excel.poc.model.Student;
import com.excel.poc.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> saveAllStudent(List<Student> students) {
		// TODO Auto-generated method stub
		return this.studentRepository.saveAll(students);
	}

}
