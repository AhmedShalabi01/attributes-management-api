package org.pacs.attributesmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LiveAccessPointAttributesModel {

    @NotBlank
    @JsonProperty("ID")
    private String id;

    @NotBlank
    @JsonProperty("LC")
    private String location;

    @NotNull
    @JsonProperty("IT")
    private Boolean isTampered;

    @NotNull
    @JsonProperty("OL")
    private int occupancyLevel;
}
