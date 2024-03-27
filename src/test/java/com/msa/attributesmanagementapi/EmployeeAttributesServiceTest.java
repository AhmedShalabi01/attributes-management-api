package com.msa.attributesmanagementapi;

import com.msa.attributesmanagementapi.document.employee.EmployeeAttributes;
import com.msa.attributesmanagementapi.document.TimeSchedule;
import com.msa.attributesmanagementapi.mapper.EmployeeAttributesMapper;
import com.msa.attributesmanagementapi.model.EmployeeAttributesModel;
import com.msa.attributesmanagementapi.repo.EmployeeAttributesRepository;
import com.msa.attributesmanagementapi.service.EmployeeAttributesService;
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
    private EmployeeAttributesMapper userMapper;

    @InjectMocks
    private EmployeeAttributesService service;

    @Test
    void getAllUsersAttributes_success() {
        // Setup - Test Documents
        List<EmployeeAttributes> documents = List.of(
                new EmployeeAttributes("1", "Developer", "Engineering",
                        new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                                Set.of("MONDAY", "TUESDAY")),
                        5, "Secret", "Full-Time"),
                new EmployeeAttributes("2", "Manager", "HR",
                        new TimeSchedule(LocalTime.of(8, 30), LocalTime.of(17, 30),
                                Set.of("MONDAY", "WEDNESDAY")),
                        10, "Top Secret", "Contract")
        );

        // Setup - Test Models
        List<EmployeeAttributesModel> expectedModels = List.of(
                new EmployeeAttributesModel("1", "Developer", "Engineering",
                        new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                                Set.of("MONDAY", "TUESDAY")),
                        5, "Secret", "Full-Time"),
                new EmployeeAttributesModel("2", "Manager", "HR",
                        new TimeSchedule(LocalTime.of(8, 30), LocalTime.of(17, 30),
                                Set.of("MONDAY", "WEDNESDAY")),
                        10, "Top Secret", "Contract")
        );

        // Setup Mocks
        when(repository.findAll()).thenReturn(documents);
        when(userMapper.toModel(any(EmployeeAttributes.class))).thenAnswer(invocation -> {
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
        verify(userMapper, times(documents.size())).toModel(any());
    }

    @Test
    void createNewUserAttributes_valid() {
        EmployeeAttributesModel userModel = new EmployeeAttributesModel("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Secret", "Full-Time");

        when(userMapper.toDocument(userModel)).thenReturn(new EmployeeAttributes("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Secret", "Full-Time"));

        service.createNewEmployeeAttributes(userModel);

        verify(userMapper).toDocument(userModel);
        verify(repository).insert(any(EmployeeAttributes.class));
    }


    @Test
    void updateUserAttributes_userFound() {
        String userId = "1";
        EmployeeAttributesModel userModel = new EmployeeAttributesModel("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Secret", "Full-Time");

        EmployeeAttributes existingDocument = new EmployeeAttributes("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Secret", "Full-Time");
        when(repository.findById(userId)).thenReturn(Optional.of(existingDocument));
        when(userMapper.toDocument(userModel)).thenReturn(existingDocument);

        service.updateEmployeeAttributes(userModel, userId);

        verify(repository).findById(userId);
        verify(userMapper).toModel(existingDocument);
        verify(userMapper).toDocument(userModel);
        verify(repository).save(any(EmployeeAttributes.class));
    }


    @Test
    void deleteUserAttributes_userFound() {
        String userId = "1";
        EmployeeAttributes existingDocument = new EmployeeAttributes("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Secret", "Full-Time");
        when(repository.findById(userId)).thenReturn(Optional.of(existingDocument));

        service.deleteEmployeeAttributes(userId);

        verify(repository).findById(userId);
        verify(userMapper).toModel(existingDocument);
        verify(repository).deleteById(userId);
    }
}

