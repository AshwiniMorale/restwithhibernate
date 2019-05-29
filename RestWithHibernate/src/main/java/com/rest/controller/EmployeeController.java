package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.beans.Employee;
import com.rest.dao.EmployeeDao;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeDao employeeDao;

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeDao.readAll();
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity getEmployee(@PathVariable("id") Integer id) {
		Employee employee = employeeDao.readById(id);
		if (employee == null)
			return new ResponseEntity("no employee found" + id, HttpStatus.NOT_FOUND);

		return new ResponseEntity(employee, HttpStatus.OK);
	}

	@PostMapping("/insertemp")
	private ResponseEntity postEmployee(@RequestBody Employee employee) {
		employeeDao.insert(employee);
		System.out.println("save object ");
		return new ResponseEntity(employee, HttpStatus.OK);
	}

	@PutMapping("/updateemp/{id}")
	public ResponseEntity update(@PathVariable Integer id, @RequestBody Employee employee) {

		if (employee.getId() == null)
			return new ResponseEntity("id not present" + id, HttpStatus.NOT_FOUND);

		return new ResponseEntity(employee, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		if (null == employeeDao.delete(id))
			return new ResponseEntity("id not present" + id, HttpStatus.NOT_FOUND);

		return new ResponseEntity(id, HttpStatus.OK);
	}
}
