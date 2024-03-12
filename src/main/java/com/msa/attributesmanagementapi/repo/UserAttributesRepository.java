package com.msa.attributesmanagementapi.repo;

import com.msa.attributesmanagementapi.document.UserAttributes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttributesRepository  extends MongoRepository<UserAttributes,String> {
}
