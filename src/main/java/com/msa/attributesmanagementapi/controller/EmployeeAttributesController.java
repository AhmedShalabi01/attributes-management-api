package com.msa.attributesmanagementapi.controller;

import com.msa.attributesmanagementapi.model.EmployeeAttributesModel;
import com.msa.attributesmanagementapi.service.EmployeeAttributesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users-attributes/employee")
@RequiredArgsConstructor
public class EmployeeAttributesController {

    private final EmployeeAttributesService employeeService;

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeAttributesModel>> getAllEmployeesAttributes(){
        return new ResponseEntity<>(employeeService.getAllEmployeesAttributes(), HttpStatus.OK);
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<EmployeeAttributesModel> findEmployeeAttributes(@PathVariable String id){
        return new ResponseEntity<>(employeeService.findEmployeeAttributes(id), HttpStatus.OK);
    }


    @PostMapping(path = "/add")
    public ResponseEntity<String> createNewEmployeeAttributes(@RequestBody EmployeeAttributesModel employeeModel){
        employeeService.createNewEmployeeAttributes(employeeModel);
        return new ResponseEntity<>("Employee Attributes Created",HttpStatus.CREATED);
    }


    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateEmployeeAttributes(@RequestBody EmployeeAttributesModel employeeModel, @PathVariable String id){
        employeeService.updateEmployeeAttributes(employeeModel,id);
        return new ResponseEntity<>("Employee Attributes Updated",HttpStatus.OK);
    }


    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<String> deleteEmployeeAttributes(@PathVariable String id){
        employeeService.deleteEmployeeAttributes(id);
        return new ResponseEntity<>("Employee Attributes Deleted",HttpStatus.OK);
    }
}
