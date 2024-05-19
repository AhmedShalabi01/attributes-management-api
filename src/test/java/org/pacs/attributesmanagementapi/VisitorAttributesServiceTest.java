package org.pacs.attributesmanagementapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pacs.attributesmanagementapi.document.TimeSchedule;
import org.pacs.attributesmanagementapi.document.VisitorAttributes;
import org.pacs.attributesmanagementapi.mapper.VisitorAttributesMapper;
import org.pacs.attributesmanagementapi.model.VisitorAttributesModel;
import org.pacs.attributesmanagementapi.repo.VisitorAttributesRepository;
import org.pacs.attributesmanagementapi.service.VisitorAttributesService;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitorAttributesServiceTest {

    @Mock
    private VisitorAttributesRepository repository;

    @Mock
    private VisitorAttributesMapper visitorMapper;

    @InjectMocks
    private VisitorAttributesService service;

    @Test
    void getAllVisitorsAttributes_success() {
        // Setup - Test Documents
        List<VisitorAttributes> documents = List.of(
                new VisitorAttributes("1", "Engineering", "Visitor",
                        new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                                Set.of("MONDAY", "TUESDAY")),"Level 1"),
                new VisitorAttributes("2", "HR", "Visitor",
                        new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                                Set.of("MONDAY", "TUESDAY")),"Level 2")
        );


        List<VisitorAttributesModel> expectedModels = List.of(
                new VisitorAttributesModel("1", "Engineering", "Visitor",
                        new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                                Set.of("MONDAY", "TUESDAY")),"Level 1"),
                new VisitorAttributesModel("2", "HR", "Visitor",
                        new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                                Set.of("MONDAY", "TUESDAY")),"Level 2")
        );

        when(repository.findAll()).thenReturn(documents);
        when(visitorMapper.toModel(any(VisitorAttributes.class))).thenAnswer(invocation -> {
            VisitorAttributes doc = invocation.getArgument(0);
            return new VisitorAttributesModel(doc.getId(), doc.getDepartment(), doc.getRole(),
                    doc.getTimeSchedule(),doc.getClearanceLevel());
        });


        List<VisitorAttributesModel> actualModels = service.getAllVisitorsAttributes();


        assertThat(actualModels).isEqualTo(expectedModels);
        verify(repository).findAll();
        verify(visitorMapper, times(documents.size())).toModel(any());
    }

    @Test
    void createNewVisitorAttributes_valid() {
        VisitorAttributesModel visitorModel = new VisitorAttributesModel("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                "Level 1");

        when(visitorMapper.toDocument(visitorModel)).thenReturn(new VisitorAttributes("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                "Level 1"));

        service.createNewVisitorAttributes(visitorModel);

        verify(visitorMapper).toDocument(visitorModel);
        verify(repository).save(any(VisitorAttributes.class));
    }


    @Test
    void updateVisitorAttributes_userFound() {
        String visitorId = "1";
        VisitorAttributesModel visitorModel = new VisitorAttributesModel("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                "Level 1");

        VisitorAttributes existingDocument = new VisitorAttributes("1", "Engineering", "Visitor",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY", "TUESDAY")),"Level 1");

        when(repository.findById(visitorId)).thenReturn(Optional.of(existingDocument));
        when(visitorMapper.toDocument(visitorModel)).thenReturn(existingDocument);

        service.updateVisitorAttributes(visitorModel, visitorId);

        verify(repository).findById(visitorId);
        verify(visitorMapper).toModel(existingDocument);
        verify(visitorMapper).toDocument(visitorModel);
        verify(repository).save(any(VisitorAttributes.class));
    }


    @Test
    void deleteVisitorAttributes_userFound() {
        String visitorId = "1";
        VisitorAttributes existingDocument = new VisitorAttributes("1", "Engineering", "Visitor",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY", "TUESDAY")),"Level 1");
        when(repository.findById(visitorId)).thenReturn(Optional.of(existingDocument));

        service.deleteVisitorAttributes(visitorId);

        verify(repository).findById(visitorId);
        verify(visitorMapper).toModel(existingDocument);
        verify(repository).deleteById(visitorId);
    }
}