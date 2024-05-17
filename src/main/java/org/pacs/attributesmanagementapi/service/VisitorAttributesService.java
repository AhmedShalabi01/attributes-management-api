package org.pacs.attributesmanagementapi.service;

import org.pacs.attributesmanagementapi.mapper.VisitorAttributesMapper;
import org.pacs.attributesmanagementapi.model.VisitorAttributesModel;
import org.pacs.attributesmanagementapi.repo.VisitorAttributesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
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
    private final VisitorAttributesMapper Mapper;
    private final MongoOperations mongoOperations;

    public List<VisitorAttributesModel> getAllVisitorsAttributes(){

        return repository.findAll()
                .stream()
                .map(Mapper::toModel)
                .collect(Collectors.toList());

    }

    public void createNewVisitorAttributes(@Valid VisitorAttributesModel visitorModel){
        repository.save(Mapper.toDocument(visitorModel));
    }

    public void updateVisitorAttributes(@Valid VisitorAttributesModel visitorModel, String visitorId) {
        if(!visitorId.equals(visitorModel.getId())) throw new ValidationException("The Path ID and Request ID not matching");
        Mapper.toModel(repository
                .findById(visitorId)
                .orElseThrow(() -> new EntityNotFoundException("The Visitor with ID : (" + visitorId + ") does not exist")));

        repository.save(Mapper.toDocument(visitorModel));
    }

    public void deleteVisitorAttributes(String visitorId) {
        Mapper.toModel(repository
                .findById(visitorId)
                .orElseThrow(() -> new EntityNotFoundException("The Visitor with ID : (" + visitorId + ") does not exist")));

        repository.deleteById(visitorId);
    }

    public VisitorAttributesModel findVisitorAttributes(String visitorId) {
        return Mapper.toModel(repository
                .findById(visitorId)
                .orElseThrow(() -> new EntityNotFoundException("The Visitor with ID : (" + visitorId + ") does not exist")));
    }
}
