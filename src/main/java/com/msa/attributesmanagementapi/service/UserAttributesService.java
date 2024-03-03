package com.msa.attributesmanagementapi.service;

import com.msa.attributesmanagementapi.mapper.UserAttributeMapper;
import com.msa.attributesmanagementapi.model.UserAttributesModel;
import com.msa.attributesmanagementapi.repo.UserAttributesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@Transactional
@RequiredArgsConstructor
public class UserAttributesService {
    private final UserAttributesRepository repository;
    private final UserAttributeMapper userMapper;


    //-------------------------------------------------------------------------------------------//

    public List<UserAttributesModel> getAllUsersAttributes(){


        return repository.findAll()
                .stream()
                .map(userMapper::toModel)
                .collect(Collectors.toList());

    }

    //-------------------------------------------------------------------------------------------//

    public void createNewUserAttributes(@Valid UserAttributesModel userModel){

        repository.insert(userMapper.toDocument(userModel));

    }

    //-------------------------------------------------------------------------------------------//

    public void updateUserAttributes(@Valid UserAttributesModel userModel,String userId ){
        userMapper.toModel(repository
                .findById(userId)
                .orElseThrow( ()-> new RuntimeException("The User with ID : (" + userId + ") does not exist")));

        repository.save(userMapper.toDocument(userModel));
    }

    //-------------------------------------------------------------------------------------------//
    public void deleteUserAttributes(String userId){
        userMapper.toModel(repository
                .findById(userId)
                .orElseThrow( ()-> new RuntimeException("The User with ID : (" + userId + ") does not exist")));

        repository.deleteById(userId);
    }
}
