package com.example.controller;

import com.example.entity.StudentEntity;
import com.example.service.StudentView;

public class StudentController {
	
	private StudentEntity model;
	private StudentView view;
	
	public StudentController(StudentEntity model, StudentView view) {
		this.model = model;
		this.view = view;
	}

	public String getStudentName() {
		return model.getName();
	}

	public void setStudentName(String name) {
		model.setName(name);;
	}

	public String getStudentRollNo() {
		return model.getRollNo();
	}
	
	public void setStudentRollNo(String rollNo) {
		model.setRollNo(rollNo);;
	}

	public void updateView() {
		view.printStudentDetails(model.getName(), model.getRollNo());
		
	}
	
}
