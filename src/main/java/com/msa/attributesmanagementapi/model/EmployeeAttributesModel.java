package com.msa.attributesmanagementapi.model;

import com.msa.attributesmanagementapi.document.TimeSchedule;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeAttributesModel {

    @NotBlank(message = "The ID field can not be blank")
    @Min(value = 1, message = "The ID can not be zero or less")
    private String id;

    @NotBlank(message = "The role field can not be blank")
    private String role;

    @NotBlank(message = "The department field can not be blank")
    private String department;

    @Valid
    private TimeSchedule timeSchedule;

    @Min(value = 1, message = "The years of experience can not be zero or less")

    @NotNull
    private int yearsOfExperience;

    @NotBlank(message = "The clearance level field can not be blank")
    private String clearanceLevel;

    @NotBlank(message = "The employment status field can not be blank")
    private String employmentStatus;
}
