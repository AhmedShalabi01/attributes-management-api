package com.msa.attributesmanagementapi.repo;

import com.msa.attributesmanagementapi.document.UserAttributes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAttributesRepository  extends MongoRepository<UserAttributes,String> {
}
