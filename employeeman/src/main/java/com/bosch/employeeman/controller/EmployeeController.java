package com.bosch.employeeman.controller;

import java.util.List;
import java.util.Optional;

import com.bosch.employeeman.exception.EmployeeNotfoundException;
import com.bosch.employeeman.model.Employee;
import com.bosch.employeeman.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/employee")
public class EmployeeController {

    static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService service;

    @ApiOperation(value = "View a list of all employees",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved employees"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/all")
    public List<Employee> getEmployees() {

        log.info(this.getClass().getSimpleName() + " - Get all employees service is invoked.");
        return service.getEmployees();
    }

    @ApiOperation(value = "Search an employee with an ID",response = Employee.class)
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) throws Exception {

        log.info(this.getClass().getSimpleName() + " - Get employee details by id is invoked.");
        Optional<Employee> emp =  service.getEmployeeById(id);
        if(!emp.isPresent())
            throw new EmployeeNotfoundException();

        return emp.get();
    }

    @ApiOperation(value = "Add an employee")
    @PostMapping("/add")
    public ResponseEntity<String> createEmployee(@RequestBody Employee newEmp) {

        String message = "";

        log.info(this.getClass().getSimpleName() + " - Create new employee method is invoked.");
        if (newEmp!=null) {
            try {
                service.addNewEmployee(newEmp);
                message = "Employee added successfully";

                log.info(message);
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Couldn't add employee";
                log.warn(message);
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please enter valid entry!!";
        log.warn(message);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

    }

    @ApiOperation(value = "Update an employee")
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@RequestBody Employee updemp, @PathVariable int id) throws Exception {

        log.info(this.getClass().getSimpleName() + " - Update employee details by id is invoked.");

        Optional<Employee> emp =  service.getEmployeeById(id);
        if (!emp.isPresent())
            throw new EmployeeNotfoundException();

        if(updemp.getName() == null || updemp.getName().isEmpty())
            updemp.setName(emp.get().getName());
        if(updemp.getDepartment() == null || updemp.getDepartment().isEmpty())
            updemp.setDepartment(emp.get().getDepartment());
        if(updemp.getSalary() == 0)
            updemp.setSalary(emp.get().getSalary());

        updemp.setId(id);
        return service.updateEmployee(updemp);
    }

//    @PatchMapping("/partialupdate/{id}")
//    public Employee partialUpdateEmployee(@RequestBody long salary , @PathVariable int id) throws Exception {
//
//        log.info(this.getClass().getSimpleName() + " - Update employee details by id is invoked.");
//
//        Optional<Employee> emp =  service.getEmployeeById(id);
//        if (!emp.isPresent())
//            throw new Exception("Could not find employee with id- " + id);
//
//
//        emp.se
//        return service.updateEmployee(updemp);
//    }

    @ApiOperation(value = "Delete an employee")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id){

        log.info(this.getClass().getSimpleName() + " - Delete employee by id is invoked.");

        Optional<Employee> emp =  service.getEmployeeById(id);
        if(!emp.isPresent()) {
            log.warn("Employee not present to delete!!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(id+" Employee not present to delete!!");
        }
        service.deleteEmployeeById(id);
        log.warn("Deleted all entries");
        return ResponseEntity.status(HttpStatus.OK).body("Deleted all entries!!");



    }
}