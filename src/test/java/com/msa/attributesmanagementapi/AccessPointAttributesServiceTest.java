package com.msa.attributesmanagementapi;

import com.msa.attributesmanagementapi.document.accesspoint.AccessPointAttributes;
import com.msa.attributesmanagementapi.mapper.AccessPointAttributesMapper;
import com.msa.attributesmanagementapi.model.AccessPointAttributesModel;
import com.msa.attributesmanagementapi.repo.AccessPointAttributesRepository;
import com.msa.attributesmanagementapi.service.AccessPointAttributesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccessPointAttributesServiceTest {

    @Mock
    private AccessPointAttributesRepository repository;

    @Mock
    private AccessPointAttributesMapper accessPointAttributesMapper;
    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private AccessPointAttributesService service;

    @Test
    void getAllAccessPointAttributes_success() {
        // Setup - Test Documents
        List<AccessPointAttributes> documents = List.of(
                new AccessPointAttributes("1", "Building A",  false, 20),
                new AccessPointAttributes("2", "Cafeteria",  true, 5)
        );

        // Setup - Test Models
        List<AccessPointAttributesModel> expectedModels = List.of(
                new AccessPointAttributesModel("1", "Building A",  false, 20),
                new AccessPointAttributesModel("2", "Cafeteria", true, 5)
        );

        // Setup Mocks
        when(repository.findAll()).thenReturn(documents);
        when(accessPointAttributesMapper.toModel(any(AccessPointAttributes.class))).thenAnswer(invocation -> {
            AccessPointAttributes doc = invocation.getArgument(0);
            return new AccessPointAttributesModel(doc.getId(), doc.getLocation(),
                    doc.getIsTampered(), doc.getOccupancyLevel());
        });

        // Execute
        List<AccessPointAttributesModel> actualModels = service.getAllAccessPointAttributes();

        // Verify
        assertThat(actualModels).isEqualTo(expectedModels);
        verify(repository).findAll();
        verify(accessPointAttributesMapper, times(documents.size())).toModel(any());
    }

    @Test
    void createNewAccessPointAttributes_valid() {
        AccessPointAttributesModel model = new AccessPointAttributesModel("3", "Office 101", false, 8);

        when(accessPointAttributesMapper.toDocument(model)).thenReturn(new AccessPointAttributes("3", "Office 101", false, 8));
        service.createNewAccessPointAttributes(model);

        verify(accessPointAttributesMapper).toDocument(model);
        verify(repository).insert(any(AccessPointAttributes.class));
    }

    @Test
    void updateAccessPointAttributes_found() {
        String accessPointId = "1";
        AccessPointAttributesModel updatedModel = new AccessPointAttributesModel(accessPointId, "New Location", true, 15);

        AccessPointAttributes existingDocument = new AccessPointAttributes(accessPointId, "New Location", true, 15);
        when(repository.findById(accessPointId)).thenReturn(Optional.of(existingDocument));

        when(accessPointAttributesMapper.toDocument(updatedModel)).thenReturn(existingDocument);
        when(accessPointAttributesMapper.toModel(existingDocument)).thenReturn(updatedModel);

        service.updateAccessPointAttributes(updatedModel, accessPointId);

        verify(repository).findById(accessPointId);
        verify(accessPointAttributesMapper).toModel(existingDocument);
        verify(accessPointAttributesMapper).toDocument(updatedModel);
        verify(repository).save(any(AccessPointAttributes.class));
    }

    @Test
    void deleteAccessPointAttributes_found() {
        String accessPointId = "2";
        AccessPointAttributes existingDocument = new AccessPointAttributes(accessPointId, "Location", true, 15);
        when(repository.findById(accessPointId)).thenReturn(Optional.of(existingDocument));

        service.deleteAccessPointAttributes(accessPointId);

        verify(repository).findById(accessPointId);
        verify(accessPointAttributesMapper).toModel(existingDocument);
        verify(repository).deleteById(accessPointId);
    }



}
