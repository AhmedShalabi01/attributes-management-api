package org.pacs.attributesmanagementapi.mapper;

import org.pacs.attributesmanagementapi.document.VisitorAttributes;
import org.pacs.attributesmanagementapi.model.VisitorAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitorAttributesMapper {
    VisitorAttributesModel toModel(final VisitorAttributes visitorAttributes);

    VisitorAttributes toDocument(final VisitorAttributesModel visitorAttributesModel);
}
