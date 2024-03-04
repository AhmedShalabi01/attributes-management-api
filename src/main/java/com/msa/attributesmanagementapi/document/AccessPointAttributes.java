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

    public AccessPointAttributes(String id, String location, String ipAddress, Boolean isTampered, int occupancyLevel) {
        this.id = id;
        this.location = location;
        this.ipAddress = ipAddress;
        this.isTampered = isTampered;
        this.occupancyLevel = occupancyLevel;
    }
}
