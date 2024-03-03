package com.msa.attributesmanagementapi.model;

import com.msa.attributesmanagementapi.document.TimeSchedule;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserAttributesModel {


    @NotBlank(message = "The ID field Can not be blank")
    @Min(value = 1,message = "The ID can not be zero or less")
    private String id;

    @NotBlank(message = "The role field Can not be blank")
    private String role;

    @NotBlank(message = "The department field Can not be blank")
    private String department;

    @Valid
    private TimeSchedule timeSchedule;

    @Min(value = 1,message = "The years of experience can not be zero or less")
    @NotNull
    private int yearOfExperience;

    @NotBlank(message = "The clearance Level field Can not be blank")
    private String clearanceLevel;

    @NotBlank(message = "The employment status Level field Can not be blank")
    private String employmentStatus;


}
