package com.msa.attributesmanagementapi.controller;

import com.msa.attributesmanagementapi.model.VisitorAttributesModel;
import com.msa.attributesmanagementapi.service.VisitorAttributesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users-attributes/visitor")
@RequiredArgsConstructor
public class VisitorAttributesController {

    private final VisitorAttributesService visitorService;

    @GetMapping("/list")
    public ResponseEntity<List<VisitorAttributesModel>> getAllVisitorsAttributes(){
        return new ResponseEntity<>(visitorService.getAllVisitorsAttributes(), HttpStatus.OK);
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<VisitorAttributesModel> findVisitorAttributes(@PathVariable String id){
        return new ResponseEntity<>(visitorService.findVisitorAttributes(id), HttpStatus.OK);
    }


    @PostMapping(path = "/add")
    public ResponseEntity<String> createNewVisitorAttributes(@RequestBody VisitorAttributesModel visitorModel){
        visitorService.createNewVisitorAttributes(visitorModel);
        return new ResponseEntity<>("Visitor Attributes Created",HttpStatus.CREATED);
    }


    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateVisitorAttributes(@RequestBody VisitorAttributesModel visitorModel, @PathVariable String id){
        visitorService.updateVisitorAttributes(visitorModel,id);
        return new ResponseEntity<>("Visitor Attributes Updated",HttpStatus.OK);
    }


    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<String> deleteVisitorAttributes(@PathVariable String id){
        visitorService.deleteVisitorAttributes(id);
        return new ResponseEntity<>("Visitor Attributes Deleted",HttpStatus.OK);
    }
}
