package org.pacs.attributesmanagementapi.repo;

import org.pacs.attributesmanagementapi.document.VisitorAttributes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorAttributesRepository extends MongoRepository<VisitorAttributes,String> {
}
