package com.msa.attributesmanagementapi.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserAttributes {

    @Id
    private String id;
    private String role;
    private String department;
    private TimeSchedule timeSchedule;
    private int yearOfExperience;
    private String clearanceLevel;
    private String employmentStatus;


    public UserAttributes(String id, String role, String department, TimeSchedule timeSchedule, int yearOfExperience, String clearanceLevel, String employmentStatus) {
        this.id = id;
        this.role = role;
        this.department = department;
        this.timeSchedule = timeSchedule;
        this.yearOfExperience = yearOfExperience;
        this.clearanceLevel = clearanceLevel;
        this.employmentStatus = employmentStatus;
    }
}
