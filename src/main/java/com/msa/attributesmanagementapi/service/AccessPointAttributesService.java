package com.msa.attributesmanagementapi.service;

import com.msa.attributesmanagementapi.document.accesspoint.AccessPointAttributes;
import com.msa.attributesmanagementapi.document.accesspoint.AccessPointsCollectionSequence;
import com.msa.attributesmanagementapi.document.visitor.VisitorAttributes;
import com.msa.attributesmanagementapi.document.visitor.VisitorAttributesCollectionSequence;
import com.msa.attributesmanagementapi.mapper.AccessPointAttributesMapper;
import com.msa.attributesmanagementapi.model.AccessPointAttributesModel;
import com.msa.attributesmanagementapi.repo.AccessPointAttributesRepository;
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
public class AccessPointAttributesService {
    private final AccessPointAttributesRepository repository;
    private final AccessPointAttributesMapper accessPointAttributesMapper;
    private final MongoOperations mongoOperations;

    public List<AccessPointAttributesModel> getAllAccessPointAttributes() {
        return repository.findAll()
                .stream()
                .map(accessPointAttributesMapper::toModel)
                .collect(Collectors.toList());
    }

    public void createNewAccessPointAttributes(@Valid AccessPointAttributesModel accessPointAttributesModel) {
        accessPointAttributesModel.setId(generateSequence());
        repository.insert(accessPointAttributesMapper.toDocument(accessPointAttributesModel));
    }

    public void updateAccessPointAttributes(@Valid AccessPointAttributesModel accessPointAttributesModel, String accessPointId) {
        if (!accessPointId.equals(accessPointAttributesModel.getId()))
            throw new ValidationException("The Path ID and Request ID not matching");
        accessPointAttributesMapper.toModel(repository
                .findById(accessPointId)
                .orElseThrow(() -> new EntityNotFoundException("The AccessPoint with ID(" + accessPointId + ") does not exist")));

        repository.save(accessPointAttributesMapper.toDocument(accessPointAttributesModel));
    }

    public void deleteAccessPointAttributes(String accessPointId) {
        accessPointAttributesMapper.toModel(repository
                .findById(accessPointId)
                .orElseThrow(() -> new EntityNotFoundException("The AccessPoint with ID(" + accessPointId + ") does not exist")));

        repository.deleteById(accessPointId);
    }

    public AccessPointAttributesModel findAccessPointAttributesById(String accessPointId) {
        return accessPointAttributesMapper.toModel(repository
                .findById(accessPointId)
                .orElseThrow(() -> new EntityNotFoundException("The Access Point with ID(" + accessPointId + ") does not exist")));
    }

    public AccessPointAttributesModel findAccessPointAttributesByLocation(String accessPointLocation) {
        return accessPointAttributesMapper.toModel(repository
                .findByLocation(accessPointLocation)
                .orElseThrow(() -> new EntityNotFoundException("The Access Point with Location(" + accessPointLocation + ") does not exist")));
    }

    private String generateSequence() {
        AccessPointsCollectionSequence counter = mongoOperations.findAndModify(query(where("_id").is(AccessPointAttributes.SEQUENCE_NAME)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                AccessPointsCollectionSequence.class);
        return String.valueOf(!Objects.isNull(counter) ? counter.getSeq() : 1);
    }

}
