package com.msa.attributesmanagementapi.repo;

import com.msa.attributesmanagementapi.document.visitor.VisitorAttributes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorAttributesRepository extends MongoRepository<VisitorAttributes,String> {
}
