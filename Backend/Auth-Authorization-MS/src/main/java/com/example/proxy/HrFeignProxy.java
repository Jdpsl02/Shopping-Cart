package com.example.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.Employee;

@FeignClient(name = "hr-ms", url="http://localhost:9595/")
public interface HrFeignProxy {
	@GetMapping("/viewAllEmployee")
	public List<Employee> viewAllEmployee();
	
	@GetMapping("/viewEmployeeByEmpid/{id}")
	public Employee viewEmployeeByEmpid(@PathVariable("id") int id);
	
	@PostMapping("/addEmployee")
	public void addEmployee(@RequestBody Employee emp);
}
