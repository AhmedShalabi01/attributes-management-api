package com.msa.attributesmanagementapi.controller;

import com.msa.attributesmanagementapi.model.UserAttributesModel;
import com.msa.attributesmanagementapi.service.UserAttributesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users-attributes")
@RequiredArgsConstructor
public class UserAttributesController {

    private final UserAttributesService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserAttributesModel>> getAllUsersAttributes(){
        return new ResponseEntity<>(userService.getAllUsersAttributes(), HttpStatus.OK);
    }

    @GetMapping("/findUserAttributes/{id}")
    public ResponseEntity<UserAttributesModel> getUserAttributes(@PathVariable String id){
        return new ResponseEntity<>(userService.findUserAttributes(id), HttpStatus.OK);
    }


    @PostMapping(path = "/add")
    public ResponseEntity<String> createNewUserAttributes(@RequestBody UserAttributesModel userModel){
        userService.createNewUserAttributes(userModel);
        return new ResponseEntity<>("User Attributes Created",HttpStatus.CREATED);
    }


    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateUserAttributes(@RequestBody UserAttributesModel userModel,@PathVariable String id){
        userService.updateUserAttributes(userModel,id);
        return new ResponseEntity<>("User Attributes Updated",HttpStatus.OK);
    }


    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<String> deleteUserAttributes(@PathVariable String id){
        userService.deleteUserAttributes(id);
        return new ResponseEntity<>("User Attributes Deleted",HttpStatus.OK);
    }
}
