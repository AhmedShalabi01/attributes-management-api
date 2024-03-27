package com.msa.attributesmanagementapi.mapper;

import com.msa.attributesmanagementapi.document.visitor.VisitorAttributes;
import com.msa.attributesmanagementapi.model.VisitorAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitorAttributesMapper {
    VisitorAttributesModel toModel(final VisitorAttributes visitorAttributes);

    VisitorAttributes toDocument(final VisitorAttributesModel visitorAttributesModel);
}
