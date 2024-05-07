package org.pacs.attributesmanagementapi.controller;

import org.pacs.attributesmanagementapi.model.AccessPointAttributesModel;
import lombok.RequiredArgsConstructor;
import org.pacs.attributesmanagementapi.model.LiveAccessPointAttributesModel;
import org.pacs.attributesmanagementapi.service.AccessPointAttributesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/access-points-attributes")
public class AccessPointAttributesController {

    private final AccessPointAttributesService accessPointService;

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

    @PutMapping(path = "/admin-update/{id}")
    public ResponseEntity<String> updateAllAccessPointAttributes(@RequestBody AccessPointAttributesModel accessPointAttributesModel, @PathVariable String id){
        accessPointService.updateAllAccessPointAttributes(accessPointAttributesModel,id);
        return new ResponseEntity<>("Access Point Attributes Updated",HttpStatus.OK);
    }

    @PutMapping(path = "/live-update/{location}")
    public ResponseEntity<String> updateLiveAccessPointAttributes(@RequestBody LiveAccessPointAttributesModel liveAccessPointAttributesModel, @PathVariable String location){
        accessPointService.updateLiveAccessPointAttributes(liveAccessPointAttributesModel,location);
        return new ResponseEntity<>("Access Point Attributes Updated",HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<String> deleteAccessPointAttributes(@PathVariable String id){
        accessPointService.deleteAccessPointAttributes(id);
        return new ResponseEntity<>("Access Point Attributes Deleted",HttpStatus.OK);
    }
}
