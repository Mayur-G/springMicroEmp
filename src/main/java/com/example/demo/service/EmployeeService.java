package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeResponse;

public interface EmployeeService {

	public EmployeeResponse getEmployeeDetails(int id);
}
