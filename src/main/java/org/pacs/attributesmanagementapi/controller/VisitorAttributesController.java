package org.pacs.attributesmanagementapi.controller;

import org.pacs.attributesmanagementapi.model.VisitorAttributesModel;
import org.pacs.attributesmanagementapi.service.VisitorAttributesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users-attributes/visitor")
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
    public ResponseEntity<Void> createNewVisitorAttributes(@RequestBody VisitorAttributesModel visitorModel){
        visitorService.createNewVisitorAttributes(visitorModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Void> updateVisitorAttributes(@RequestBody VisitorAttributesModel visitorModel, @PathVariable String id){
        visitorService.updateVisitorAttributes(visitorModel,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<Void> deleteVisitorAttributes(@PathVariable String id){
        visitorService.deleteVisitorAttributes(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
