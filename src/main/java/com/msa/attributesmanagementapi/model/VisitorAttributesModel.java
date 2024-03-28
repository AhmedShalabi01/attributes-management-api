package com.msa.attributesmanagementapi.model;

import com.msa.attributesmanagementapi.document.TimeSchedule;
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
    private String id;

    @NotBlank(message = "The role field Can not be blank")
    private String role;
    @Valid
    private TimeSchedule timeSchedule;
    @NotBlank(message = "The clearance Level field Can not be blank")
    private String clearanceLevel;

}
