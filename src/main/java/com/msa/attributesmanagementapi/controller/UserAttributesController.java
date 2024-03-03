package com.msa.attributesmanagementapi.controller;

import com.msa.attributesmanagementapi.model.UserAttributesModel;
import com.msa.attributesmanagementapi.service.UserAttributesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacs/users/attributes")
public class UserAttributesController {

    private final UserAttributesService userService;

    public UserAttributesController(UserAttributesService userService) {
        this.userService = userService;
    }

    //-------------------------------------------------------------------------------------------//

    @GetMapping
    public ResponseEntity<List<UserAttributesModel>> getAllUsersAttributes(){
        return new ResponseEntity<>(userService.getAllUsersAttributes(), HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------//

    @PostMapping(path = "/insertNewUserAttributes")
    public ResponseEntity<String> createNewUserAttributes(@RequestBody UserAttributesModel userModel){
        userService.createNewUserAttributes(userModel);
        return new ResponseEntity<>("User Attributes Created",HttpStatus.CREATED);
    }

    //-------------------------------------------------------------------------------------------//

    @PutMapping(path = "/updateUserAttributes/{id}")
    public ResponseEntity<String> updateUserAttributes(@RequestBody UserAttributesModel userModel,@PathVariable String id){
        userService.updateUserAttributes(userModel,id);
        return new ResponseEntity<>("User Attributes Updated",HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------//

    @DeleteMapping (value = "/deleteUserAttributes/{id}")
    public ResponseEntity<String> deleteUserAttributes(@PathVariable String id){
        userService.deleteUserAttributes(id);
        return new ResponseEntity<>("User Attributes Deleted",HttpStatus.OK);
    }
}
