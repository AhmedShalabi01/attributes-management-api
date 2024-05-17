package org.pacs.attributesmanagementapi.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.pacs.attributesmanagementapi.document.accesspoint.AccessPointAttributes;
import org.pacs.attributesmanagementapi.document.accesspoint.AccessPointsCollectionSequence;
import org.pacs.attributesmanagementapi.mapper.AccessPointAttributesMapper;
import org.pacs.attributesmanagementapi.model.AccessPointAttributesModel;
import org.pacs.attributesmanagementapi.model.LiveAccessPointAttributesModel;
import org.pacs.attributesmanagementapi.repo.AccessPointAttributesRepository;
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
        repository.save(accessPointAttributesMapper.toDocument(accessPointAttributesModel));
    }

    public void updateAllAccessPointAttributes(@Valid AccessPointAttributesModel accessPointAttributesModel, String accessPointId) {
        if (!accessPointId.equals(accessPointAttributesModel.getId()))
            throw new ValidationException("The Path ID and Request ID not matching");
        accessPointAttributesMapper.toModel(repository
                .findById(accessPointId)
                .orElseThrow(() -> new EntityNotFoundException("The AccessPoint with ID(" + accessPointId + ") does not exist")));

        repository.save(accessPointAttributesMapper.toDocument(accessPointAttributesModel));
    }

    public void updateLiveAccessPointAttributes(@Valid LiveAccessPointAttributesModel liveAccessPointAttributesModel, String location) {
        if (!location.equals(liveAccessPointAttributesModel.getLocation()))
            throw new ValidationException("The Path location and Request location not matching");

        AccessPointAttributesModel accessPointAttributesModel = accessPointAttributesMapper.toModel(repository
                .findByLocation(location)
                .orElseThrow(() -> new EntityNotFoundException("The AccessPoint with location (" + location + ") does not exist")));

        // Update live attributes from model
        accessPointAttributesModel.setIsTampered(liveAccessPointAttributesModel.getIsTampered());
        accessPointAttributesModel.setOccupancyLevel(liveAccessPointAttributesModel.getOccupancyLevel());

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
