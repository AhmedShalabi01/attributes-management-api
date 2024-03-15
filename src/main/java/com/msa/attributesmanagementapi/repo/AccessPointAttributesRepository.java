package com.msa.attributesmanagementapi.repo;

import com.msa.attributesmanagementapi.document.AccessPointAttributes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessPointAttributesRepository extends MongoRepository<AccessPointAttributes,String> {
    Optional<AccessPointAttributes> findByLocation(String location);
}
