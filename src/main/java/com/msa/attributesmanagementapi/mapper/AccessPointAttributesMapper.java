package com.msa.attributesmanagementapi.mapper;

import com.msa.attributesmanagementapi.document.accesspoint.AccessPointAttributes;
import com.msa.attributesmanagementapi.model.AccessPointAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface    AccessPointAttributesMapper {

    AccessPointAttributesModel toModel(final AccessPointAttributes accessPointAttributes);

    AccessPointAttributes toDocument(final AccessPointAttributesModel accessPointAttributesModel);
}
