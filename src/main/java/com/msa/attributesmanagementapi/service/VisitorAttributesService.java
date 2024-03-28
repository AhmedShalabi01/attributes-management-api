package com.msa.attributesmanagementapi.service;


import com.msa.attributesmanagementapi.mapper.VisitorAttributesMapper;
import com.msa.attributesmanagementapi.model.VisitorAttributesModel;
import com.msa.attributesmanagementapi.repo.VisitorAttributesRepository;
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
public class VisitorAttributesService {
    private final VisitorAttributesRepository repository;
    private final VisitorAttributesMapper mapper;

    public List<VisitorAttributesModel> getAllVisitorsAttributes(){

        return repository.findAll()
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());

    }

    public void createNewVisitorAttributes(@Valid VisitorAttributesModel visitorModel){
        repository.insert(mapper.toDocument(visitorModel));
    }

    public void updateVisitorAttributes(@Valid VisitorAttributesModel visitorModel, String visitorId) {
        if(!visitorId.equals(visitorModel.getId())) throw new ValidationException("The Path ID and Request ID not matching");
        mapper.toModel(repository
                .findById(visitorId)
                .orElseThrow(() -> new EntityNotFoundException("The Visitor with ID : (" + visitorId + ") does not exist")));

        repository.save(mapper.toDocument(visitorModel));
    }

    public void deleteVisitorAttributes(String visitorId) {
        mapper.toModel(repository
                .findById(visitorId)
                .orElseThrow(() -> new EntityNotFoundException("The Visitor with ID : (" + visitorId + ") does not exist")));

        repository.deleteById(visitorId);
    }

    public VisitorAttributesModel findVisitorAttributes(String visitorId) {
        return mapper.toModel(repository
                .findById(visitorId)
                .orElseThrow(() -> new EntityNotFoundException("The Visitor with ID : (" + visitorId + ") does not exist")));
    }

}
