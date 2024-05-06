package org.pacs.attributesmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessPointAttributesModel {

    @Min(value = 1, message = "The ID can not be zero or less")
    @JsonProperty("ID")
    private String id;

    @NotBlank(message = "The location field can not be blank")
    @JsonProperty("LC")
    private String location;

    @NotNull
    @JsonProperty("IT")
    private Boolean isTampered;

    @NotNull
    @Min(value = 0, message = "The occupancy level can not be less than zero")
    @JsonProperty("OL")
    private int occupancyLevel;

    @NotNull
    @Min(value = 1, message = "The maximum occupancy level can not be zero or less")
    @JsonProperty("MOL")
    private Integer maxOccupancyLevel;
}
