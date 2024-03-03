package com.msa.attributesmanagementapi.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AccessPointAttributes {

    @Id
    private String id;
    @Indexed(unique = true)
    private String location;
    private String ipAddress;
    private Boolean isTampered;
    private int occupancyLevel;

}
