package com.mahesh.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahesh.employeeservice.EmployeeService;
import com.mahesh.entity.Employee;
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/Emp")
@CrossOrigin("*")
public class EmployeeController {
	@Autowired
	EmployeeService service;
 
	private static Logger logger = (Logger) LoggerFactory.getLogger(EmployeeController.class);
	
	@PostMapping
	public ResponseEntity<Employee> saveEmp(@RequestBody Employee employee) {
		Employee a= null;
		logger.info("upload the employee details in database");
		try {
			if(employee.getEmpName().equals("")||employee.getEmpSalary()==0) {
				logger.warn("insufficent data ");
		 		return (ResponseEntity<Employee>) new ResponseEntity("pass the data in feilds!!",HttpStatus.BAD_REQUEST);
			}
			a= service.saveEmp(employee);
			return ResponseEntity.of(Optional.of(a));
			
		}catch(Exception e) {
			
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
		
		
	}
	@GetMapping("/findall")
	public ResponseEntity<List<Employee>> findallemp() {
		
		List<Employee>list=service.findAllEmp();
		
		logger.info("Getting all employee list");
		
		if(list.isEmpty())
		{
			logger.warn("Employee list not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(list));
		
	}
	@GetMapping("/findById/{id}")
	public ResponseEntity<Optional<Employee>> getEmpById(@PathVariable("id") int empId) {

		Optional<Employee> employee = service.getEmpById(empId);
		
		logger.info("Getting  employee list by id");

		if (employee==null) {
			logger.warn("Employee not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(employee));
		
	}
	@PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmp(@RequestBody Employee employee) {
		logger.info("update employee list in database");
		try {
			if(employee.getEmpName().equals("")||employee.getEmpSalary()==0) {
				logger.warn("insufficent data");
				return (ResponseEntity<Employee>) new ResponseEntity("pass the data in feilds!!",HttpStatus.BAD_REQUEST);
			}
			service.update(employee);
			return ResponseEntity.ok().body(employee);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		

	@DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Employee> deleteEmp(@PathVariable("id") int empId) {
		logger.info("delete the employee by id");
		try {
			if(empId==0) {
				logger.warn("insufficent data");
				return (ResponseEntity<Employee>) new ResponseEntity("pass the data in feilds!!",HttpStatus.BAD_REQUEST);
				
			}
			service.deleteEmp(empId);
			logger.info("employee data is deleted");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
}

