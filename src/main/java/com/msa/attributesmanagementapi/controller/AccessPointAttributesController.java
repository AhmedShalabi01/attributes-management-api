package com.msa.attributesmanagementapi.controller;

import com.msa.attributesmanagementapi.model.AccessPointAttributesModel;
import com.msa.attributesmanagementapi.service.AccessPointAttributesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacs/accesspoints/attributes")
public class AccessPointAttributesController {

    private final AccessPointAttributesService accessPointService;


    public AccessPointAttributesController(AccessPointAttributesService accessPointService) {
        this.accessPointService = accessPointService;
    }

    //-------------------------------------------------------------------------------------------//

    @GetMapping
    public ResponseEntity<List<AccessPointAttributesModel>> getAllAccessPointAttributes(){
        return new ResponseEntity<>(accessPointService.getAllAccessPointAttributes(), HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------//

    @PostMapping(path = "/insertNewAccessPointAttributes")
    public ResponseEntity<String> createNewAccessPointAttributes(@RequestBody AccessPointAttributesModel accessPointAttributesModel){
        accessPointService.createNewAccessPointAttributes(accessPointAttributesModel);
        return new ResponseEntity<>("Access Point Attributes Created",HttpStatus.CREATED);
    }

    //-------------------------------------------------------------------------------------------//

    @PutMapping(path = "/updateAccessPointAttributes/{id}")
    public ResponseEntity<String> updateAccessPointAttributes(@RequestBody AccessPointAttributesModel accessPointAttributesModel,@PathVariable String id){
        accessPointService.updateAccessPointAttributes(accessPointAttributesModel,id);
        return new ResponseEntity<>("Access Point Attributes Updated",HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------//

    @DeleteMapping (value = "/deleteAccessPointAttributes/{id}")
    public ResponseEntity<String> deleteAccessPointAttributes(@PathVariable String id){
        accessPointService.deleteAccessPointAttributes(id);
        return new ResponseEntity<>("Access Point Attributes Deleted",HttpStatus.OK);
    }
}
