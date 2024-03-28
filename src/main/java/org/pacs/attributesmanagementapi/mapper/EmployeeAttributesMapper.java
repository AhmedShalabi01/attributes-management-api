package org.pacs.attributesmanagementapi.mapper;

import org.pacs.attributesmanagementapi.document.EmployeeAttributes;
import org.pacs.attributesmanagementapi.model.EmployeeAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeAttributesMapper {

    EmployeeAttributesModel toModel(final EmployeeAttributes employeeAttributes);

    EmployeeAttributes toDocument(final EmployeeAttributesModel employeeAttributesModel);
}
