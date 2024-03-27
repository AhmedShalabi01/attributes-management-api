package com.msa.attributesmanagementapi.document.accesspoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor

@Document(collection = "accessPointAttributes")

public class AccessPointAttributes {

    @Transient
    public static final String SEQUENCE_NAME = "access_points_sequence";

    @Id
    private String id;
    @Indexed(unique = true)
    private String location;
    private Boolean isTampered;
    private Integer occupancyLevel;
}
