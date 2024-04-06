package com.msa.attributesmanagementapi.document.visitor;

import com.msa.attributesmanagementapi.document.TimeSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "visitorAttributes")
public class VisitorAttributes {
    @Id
    private String id;
    private String department;
    private String role;
    private TimeSchedule timeSchedule;
    private String clearanceLevel;
}
