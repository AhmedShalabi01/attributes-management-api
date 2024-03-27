package com.msa.attributesmanagementapi.repo;

import com.msa.attributesmanagementapi.document.employee.EmployeeAttributes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAttributesRepository extends MongoRepository<EmployeeAttributes,String> {
}
