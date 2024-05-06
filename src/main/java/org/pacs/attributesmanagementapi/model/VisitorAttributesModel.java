package org.pacs.attributesmanagementapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.pacs.attributesmanagementapi.document.TimeSchedule;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VisitorAttributesModel {
    @NotBlank(message = "The ID field Can not be blank")
    @Min(value = 1,message = "The ID can not be zero or less")
    @JsonProperty("ID")
    private String id;

    @NotBlank(message = "The department field Can not be blank")
    @JsonProperty("DP")
    private String department;

    @NotBlank(message = "The role field Can not be blank")
    @JsonProperty("RL")
    private String role;

    @Valid
    @JsonProperty("TS")
    private TimeSchedule timeSchedule;

    @NotBlank(message = "The clearance Level field Can not be blank")
    @JsonProperty("CL")
    private String clearanceLevel;
}
