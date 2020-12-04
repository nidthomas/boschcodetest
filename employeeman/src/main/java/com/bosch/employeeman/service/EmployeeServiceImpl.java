package com.bosch.employeeman.service;

import com.bosch.employeeman.model.Employee;
import com.bosch.employeeman.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(int empId) {

        return employeeRepo.findById(empId);
    }

    @Override
    public Employee addNewEmployee(Employee emp) {

        return employeeRepo.save(emp);
    }

    @Override
    public Employee updateEmployee(Employee emp) {

        return employeeRepo.save(emp);
    }

    @Override
    public void deleteEmployeeById(int empId) {

        employeeRepo.deleteById(empId);

    }
}
