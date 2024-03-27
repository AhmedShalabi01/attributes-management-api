package com.msa.attributesmanagementapi.controller;

import com.msa.attributesmanagementapi.model.AccessPointAttributesModel;
import com.msa.attributesmanagementapi.service.AccessPointAttributesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access-points-attributes")
public class AccessPointAttributesController {

    private final AccessPointAttributesService accessPointService;


    public AccessPointAttributesController(AccessPointAttributesService accessPointService) {
        this.accessPointService = accessPointService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<AccessPointAttributesModel>> getAllAccessPointAttributes(){
        return new ResponseEntity<>(accessPointService.getAllAccessPointAttributes(), HttpStatus.OK);
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<AccessPointAttributesModel> getAccessPointAttributesById(@PathVariable String id){
        return new ResponseEntity<>(accessPointService.findAccessPointAttributesById(id), HttpStatus.OK);
    }

    @GetMapping("/find/location/{location}")
    public ResponseEntity<AccessPointAttributesModel> getAccessPointAttributesByLocation(@PathVariable String location){
        return new ResponseEntity<>(accessPointService.findAccessPointAttributesByLocation(location), HttpStatus.OK);
    }


    @PostMapping(path = "/add")
    public ResponseEntity<String> createNewAccessPointAttributes(@RequestBody AccessPointAttributesModel accessPointAttributesModel){
        accessPointService.createNewAccessPointAttributes(accessPointAttributesModel);
        return new ResponseEntity<>("Access Point Attributes Created",HttpStatus.CREATED);
    }


    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateAccessPointAttributes(@RequestBody AccessPointAttributesModel accessPointAttributesModel,@PathVariable String id){
        accessPointService.updateAccessPointAttributes(accessPointAttributesModel,id);
        return new ResponseEntity<>("Access Point Attributes Updated",HttpStatus.OK);
    }


    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<String> deleteAccessPointAttributes(@PathVariable String id){
        accessPointService.deleteAccessPointAttributes(id);
        return new ResponseEntity<>("Access Point Attributes Deleted",HttpStatus.OK);
    }
}
