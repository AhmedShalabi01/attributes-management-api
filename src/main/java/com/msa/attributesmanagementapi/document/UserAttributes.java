package com.msa.attributesmanagementapi.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "userAttributes")
public class UserAttributes {

    @Id
    private String id;
    private String role;
    private String department;
    private TimeSchedule timeSchedule;
    private Integer yearsOfExperience;
    private String clearanceLevel;
    private String employmentStatus;
}
