package com.excel.poc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.poc.model.Student;
import com.excel.poc.service.StudentService;

@RestController
@CrossOrigin("*")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	
	@PostMapping("/upload")
	public List<Student> uploadExcel(@RequestParam("file") MultipartFile file) throws EncryptedDocumentException, IOException{
		
		List<Student> students = new ArrayList<>();
		
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		int rowNum=0;
		
		
		
		for (Row row : sheet) {
			if(rowNum>0) {
			Student stud = new Student();
			String firstName=row.getCell(1).toString();
			String lastName=row.getCell(2).toString();
			String roll=row.getCell(3).toString();
			String address=row.getCell(4).toString();
			Long phoned=(long) row.getCell(5).getNumericCellValue();
			System.out.println("Here phone number is coming : "+phoned);
			String phone=row.getCell(5).toString();
			stud.setFirstName(firstName);
			stud.setLastName(lastName);
			stud.setAddress(address);
			stud.setRollNo(roll);
			stud.setPhoneNumber(phoned);
			
			students.add(stud);
		}
		rowNum++;	
	}
		this.studentService.saveAllStudent(students);
		
		return students;
		
	}

}
