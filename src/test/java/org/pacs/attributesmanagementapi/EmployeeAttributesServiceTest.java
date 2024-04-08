package org.pacs.attributesmanagementapi;

import org.pacs.attributesmanagementapi.document.EmployeeAttributes;
import org.pacs.attributesmanagementapi.document.TimeSchedule;
import org.pacs.attributesmanagementapi.mapper.EmployeeAttributesMapper;
import org.pacs.attributesmanagementapi.model.EmployeeAttributesModel;
import org.pacs.attributesmanagementapi.repo.EmployeeAttributesRepository;
import org.pacs.attributesmanagementapi.service.EmployeeAttributesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class EmployeeAttributesServiceTest {

    @Mock
    private EmployeeAttributesRepository repository;

    @Mock
    private EmployeeAttributesMapper employeeMapper;

    @InjectMocks
    private EmployeeAttributesService service;

    @Test
    void getAllEmployeesAttributes_success() {
        // Setup - Test Documents
        List<EmployeeAttributes> documents = List.of(
                new EmployeeAttributes("1", "Developer", "Engineering",
                        new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                                Set.of("MONDAY", "TUESDAY")),
                        5, "Level 1", "Full-Time"),
                new EmployeeAttributes("2", "Manager", "HR",
                        new TimeSchedule(LocalTime.of(8, 30), LocalTime.of(17, 30),
                                Set.of("MONDAY", "WEDNESDAY")),
                        10, "Level 2", "Part-Time")
        );

        // Setup - Test Models
        List<EmployeeAttributesModel> expectedModels = List.of(
                new EmployeeAttributesModel("1", "Developer", "Engineering",
                        new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                                Set.of("MONDAY", "TUESDAY")),
                        5, "Level 1", "Full-Time"),
                new EmployeeAttributesModel("2", "Manager", "HR",
                        new TimeSchedule(LocalTime.of(8, 30), LocalTime.of(17, 30),
                                Set.of("MONDAY", "WEDNESDAY")),
                        10, "Level 2", "Part-Time")
        );

        // Setup Mocks
        when(repository.findAll()).thenReturn(documents);
        when(employeeMapper.toModel(any(EmployeeAttributes.class))).thenAnswer(invocation -> {
            EmployeeAttributes doc = invocation.getArgument(0);
            // Construct a UserAttributesModel from 'doc' (your mapping logic here)
            return new EmployeeAttributesModel(doc.getId(), doc.getRole(), doc.getDepartment(),
                    doc.getTimeSchedule(), doc.getYearsOfExperience(), doc.getClearanceLevel(),
                    doc.getEmploymentStatus());
        });

        // Execute
        List<EmployeeAttributesModel> actualModels = service.getAllEmployeesAttributes();

        // Verify
        assertThat(actualModels).isEqualTo(expectedModels);
        verify(repository).findAll();
        verify(employeeMapper, times(documents.size())).toModel(any());
    }

    @Test
    void createNewEmployeeAttributes_valid() {
        EmployeeAttributesModel employeeModel = new EmployeeAttributesModel("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Level 1", "Full-Time");

        when(employeeMapper.toDocument(employeeModel)).thenReturn(new EmployeeAttributes("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Level 1", "Full-Time"));

        service.createNewEmployeeAttributes(employeeModel);

        verify(employeeMapper).toDocument(employeeModel);
        verify(repository).insert(any(EmployeeAttributes.class));
    }


    @Test
    void updateEmployeeAttributes_userFound() {
        String employeeId = "1";
        EmployeeAttributesModel employeeModel = new EmployeeAttributesModel("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Level 1", "Full-Time");

        EmployeeAttributes existingDocument = new EmployeeAttributes("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Level 1", "Full-Time");
        when(repository.findById(employeeId)).thenReturn(Optional.of(existingDocument));
        when(employeeMapper.toDocument(employeeModel)).thenReturn(existingDocument);

        service.updateEmployeeAttributes(employeeModel, employeeId);

        verify(repository).findById(employeeId);
        verify(employeeMapper).toModel(existingDocument);
        verify(employeeMapper).toDocument(employeeModel);
        verify(repository).save(any(EmployeeAttributes.class));
    }


    @Test
    void deleteEmployeeAttributes_userFound() {
        String employeeId = "1";
        EmployeeAttributes existingDocument = new EmployeeAttributes("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Level 1", "Full-Time");
        when(repository.findById(employeeId)).thenReturn(Optional.of(existingDocument));

        service.deleteEmployeeAttributes(employeeId);

        verify(repository).findById(employeeId);
        verify(employeeMapper).toModel(existingDocument);
        verify(repository).deleteById(employeeId);
    }
}

