package org.pacs.attributesmanagementapi.mapper;

import org.pacs.attributesmanagementapi.document.accesspoint.AccessPointAttributes;
import org.pacs.attributesmanagementapi.model.AccessPointAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessPointAttributesMapper {

    AccessPointAttributesModel toModel(final AccessPointAttributes accessPointAttributes);

    AccessPointAttributes toDocument(final AccessPointAttributesModel accessPointAttributesModel);
}
