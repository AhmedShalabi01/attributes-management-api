package com.msa.attributesmanagementapi.service;

import com.msa.attributesmanagementapi.mapper.AccessPointAttributesMapper;
import com.msa.attributesmanagementapi.model.AccessPointAttributesModel;
import com.msa.attributesmanagementapi.repo.AccessPointAttributesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
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
public class AccessPointAttributesService {
    private final AccessPointAttributesRepository repository;
    private final AccessPointAttributesMapper accessPointAttributesMapper;

    public List<AccessPointAttributesModel> getAllAccessPointAttributes(){
        return repository.findAll()
                .stream()
                .map(accessPointAttributesMapper::toModel)
                .collect(Collectors.toList());
    }
    public void createNewAccessPointAttributes(@Valid AccessPointAttributesModel accessPointAttributesModel){
        repository.insert(accessPointAttributesMapper.toDocument(accessPointAttributesModel));
    }
    public void updateAccessPointAttributes(@Valid AccessPointAttributesModel accessPointAttributesModel,String accessPointId){
        if(!accessPointId.equals(accessPointAttributesModel.getId())) throw new ValidationException("The Path ID and Request ID not matching");
        accessPointAttributesMapper.toModel(repository
                .findById(accessPointId)
                .orElseThrow( ()-> new EntityNotFoundException("The AccessPoint with ID( " + accessPointId + ") does not exist")));

        repository.save(accessPointAttributesMapper.toDocument(accessPointAttributesModel));
    }
    public void deleteAccessPointAttributes(String accessPointId){
        accessPointAttributesMapper.toModel(repository
                .findById(accessPointId)
                .orElseThrow( ()-> new EntityNotFoundException("The AccessPoint with ID( " + accessPointId + ") does not exist")));

        repository.deleteById(accessPointId);
    }
    public AccessPointAttributesModel findAccessPointAttributesById(String accessPointId){
        return accessPointAttributesMapper.toModel(repository
                .findById(accessPointId)
                .orElseThrow( ()-> new EntityNotFoundException("The Access Point with ID( " + accessPointId + ") does not exist")));
    }
    public AccessPointAttributesModel findAccessPointAttributesByLocation(String accessPointLocation){
        return accessPointAttributesMapper.toModel(repository
                .findByLocation(accessPointLocation)
                .orElseThrow( ()-> new EntityNotFoundException("The Access Point with Location( " + accessPointLocation + ") does not exist")));
    }

}
