package org.pacs.attributesmanagementapi.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "employeeAttributes")
public class EmployeeAttributes {

    @Id
    private String id;
    private String role;
    private String department;
    private TimeSchedule timeSchedule;
    private Integer yearsOfExperience;
    private String clearanceLevel;
    private String employmentStatus;
}
