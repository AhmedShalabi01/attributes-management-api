package com.msa.attributesmanagementapi.mapper;

import com.msa.attributesmanagementapi.document.UserAttributes;
import com.msa.attributesmanagementapi.model.UserAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAttributeMapper {

    UserAttributesModel toModel(final UserAttributes userAttributes);

    UserAttributes toDocument(final UserAttributesModel userAttributesModel);
}
