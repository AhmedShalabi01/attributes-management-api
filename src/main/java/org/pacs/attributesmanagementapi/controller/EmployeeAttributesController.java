package org.pacs.attributesmanagementapi.controller;

import org.pacs.attributesmanagementapi.model.EmployeeAttributesModel;
import org.pacs.attributesmanagementapi.service.EmployeeAttributesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attributes-management/users-attributes/employee")
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
    public ResponseEntity<Void> createNewEmployeeAttributes(@RequestBody EmployeeAttributesModel employeeModel){
        employeeService.createNewEmployeeAttributes(employeeModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Void> updateEmployeeAttributes(@RequestBody EmployeeAttributesModel employeeModel, @PathVariable String id){
        employeeService.updateEmployeeAttributes(employeeModel,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<Void> deleteEmployeeAttributes(@PathVariable String id){
        employeeService.deleteEmployeeAttributes(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
