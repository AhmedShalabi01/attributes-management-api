package org.pacs.attributesmanagementapi.repo;

import org.pacs.attributesmanagementapi.document.EmployeeAttributes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAttributesRepository extends MongoRepository<EmployeeAttributes,String> {
}
