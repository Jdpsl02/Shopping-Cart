package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.proxy.HrFeignProxy;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("/home")
public class RequestController {
	@Autowired 
	private HrFeignProxy hrFeignProxy;
	
	
	@GetMapping("/forAdmin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@GetMapping("/forUser")
	@PreAuthorize("hasRole('USER')")
	public String userAccess() {
		return "User Board.";
	}
	
	

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/viewAllEmployee")
	public List<Employee> viewAllEmployee(){
		return hrFeignProxy.viewAllEmployee();
	}

	
	
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/viewEmployeeByEmpid/{id}")
	public Employee viewEmployeeByEmpid(@PathVariable("id") int id){
		return hrFeignProxy.viewEmployeeByEmpid(id);
	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addEmployee")
	public void addEmployee(@RequestBody Employee emp){
		hrFeignProxy.addEmployee(emp);
	}

}
