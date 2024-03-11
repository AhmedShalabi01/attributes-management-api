package com.msa.attributesmanagementapi.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessPointAttributesModel {

    @NotBlank(message = "The ID field Can not be blank")
    @Min(value = 1,message = "The ID can not be zero or less")
    private String id;

    @NotBlank(message = "The ID field Can not be blank")
    private String location;

    @NotBlank(message = "The ID field Can not be blank")
    private String ipAddress;

    @NotNull
    private Boolean isTampered;

    @NotNull
    @Min(value = 1,message = "The occupancy level can not be zero or less")
    private int occupancyLevel;
}
