package com.mahesh.employeeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mahesh.entity.Employee;
import com.mahesh.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	
    public Employee saveEmp(Employee employee) {
		
		return repo.save(employee);
	}
	
	public List<Employee> findAllEmp(){
		return repo.findAll();
	}
	
	public Optional<Employee> getEmpById(int empId) {
	
		return repo.findById(empId);
}
	
	public String deleteEmp(int empId) {
		repo.deleteById(empId);
		return "deleted ";
	}
	
	
	
	public Employee update(Employee employee) {
	
		return repo.save(employee);
	}




	
	
	
	
	}

	