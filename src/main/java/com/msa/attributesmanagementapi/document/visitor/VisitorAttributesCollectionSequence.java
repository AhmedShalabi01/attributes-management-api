package com.msa.attributesmanagementapi.document.visitor;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "visitorSequence")
@Data
@AllArgsConstructor
public class VisitorAttributesCollectionSequence {
    @Id
    private String id;
    private long seq;
}