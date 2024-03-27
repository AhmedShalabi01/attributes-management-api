package com.msa.attributesmanagementapi.mapper;

import com.msa.attributesmanagementapi.document.employee.EmployeeAttributes;
import com.msa.attributesmanagementapi.model.EmployeeAttributesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeAttributesMapper {

    EmployeeAttributesModel toModel(final EmployeeAttributes employeeAttributes);

    EmployeeAttributes toDocument(final EmployeeAttributesModel employeeAttributesModel);
}
