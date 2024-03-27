package com.msa.attributesmanagementapi.service;

import com.msa.attributesmanagementapi.document.visitor.VisitorAttributes;
import com.msa.attributesmanagementapi.document.visitor.VisitorAttributesCollectionSequence;
import com.msa.attributesmanagementapi.mapper.VisitorAttributesMapper;
import com.msa.attributesmanagementapi.model.VisitorAttributesModel;
import com.msa.attributesmanagementapi.repo.VisitorAttributesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Validated
@Service
@Transactional
@RequiredArgsConstructor
public class VisitorAttributesService {
    private final VisitorAttributesRepository repository;
    private final VisitorAttributesMapper mapper;
    private final MongoOperations mongoOperations;

    public List<VisitorAttributesModel> getAllVisitorsAttributes(){

        return repository.findAll()
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());

    }

    public void createNewVisitorAttributes(@Valid VisitorAttributesModel visitorModel){
        visitorModel.setId(generateSequence());
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

    private String generateSequence() {
        VisitorAttributesCollectionSequence counter = mongoOperations.findAndModify(query(where("_id").is(VisitorAttributes.SEQUENCE_NAME)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                VisitorAttributesCollectionSequence.class);
        return String.valueOf(!Objects.isNull(counter) ? counter.getSeq() : 1);
    }
}
