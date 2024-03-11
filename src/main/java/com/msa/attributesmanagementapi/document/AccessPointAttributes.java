package com.msa.attributesmanagementapi.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "accessPointAttributes")
public class AccessPointAttributes {

    @Id
    private String id;
    @Indexed(unique = true)
    private String location;
    private String ipAddress;
    private Boolean isTampered;
    private Integer occupancyLevel;
}
