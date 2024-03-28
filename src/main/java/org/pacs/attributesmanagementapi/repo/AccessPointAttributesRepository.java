package org.pacs.attributesmanagementapi.repo;

import org.pacs.attributesmanagementapi.document.accesspoint.AccessPointAttributes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessPointAttributesRepository extends MongoRepository<AccessPointAttributes,String> {
    Optional<AccessPointAttributes> findByLocation(String location);
}
