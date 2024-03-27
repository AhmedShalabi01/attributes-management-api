package com.msa.attributesmanagementapi.document.accesspoint;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accessPointsSequence")
@Data
@AllArgsConstructor
public class AccessPointsCollectionSequence {
    @Id
    private String id;
    private long seq;
}