package com.msa.attributesmanagementapi;

import com.msa.attributesmanagementapi.document.TimeSchedule;
import com.msa.attributesmanagementapi.document.UserAttributes;
import com.msa.attributesmanagementapi.mapper.UserAttributeMapper;
import com.msa.attributesmanagementapi.model.UserAttributesModel;
import com.msa.attributesmanagementapi.repo.UserAttributesRepository;
import com.msa.attributesmanagementapi.service.UserAttributesService;
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
class UserAttributesServiceTest {

    @Mock
    private UserAttributesRepository repository;

    @Mock
    private UserAttributeMapper userMapper;

    @InjectMocks
    private UserAttributesService service;

    @Test
    void getAllUsersAttributes_success() {
        // Setup - Test Documents
        List<UserAttributes> documents = List.of(
                new UserAttributes("1", "Developer", "Engineering",
                        new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                                Set.of("MONDAY", "TUESDAY")),
                        5, "Secret", "Full-Time"),
                new UserAttributes("2", "Manager", "HR",
                        new TimeSchedule(LocalTime.of(8, 30), LocalTime.of(17, 30),
                                Set.of("MONDAY", "WEDNESDAY")),
                        10, "Top Secret", "Contract")
        );

        // Setup - Test Models
        List<UserAttributesModel> expectedModels = List.of(
                new UserAttributesModel("1", "Developer", "Engineering",
                        new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                                Set.of("MONDAY", "TUESDAY")),
                        5, "Secret", "Full-Time"),
                new UserAttributesModel("2", "Manager", "HR",
                        new TimeSchedule(LocalTime.of(8, 30), LocalTime.of(17, 30),
                                Set.of("MONDAY", "WEDNESDAY")),
                        10, "Top Secret", "Contract")
        );

        // Setup Mocks
        when(repository.findAll()).thenReturn(documents);
        when(userMapper.toModel(any(UserAttributes.class))).thenAnswer(invocation -> {
            UserAttributes doc = invocation.getArgument(0);
            // Construct a UserAttributesModel from 'doc' (your mapping logic here)
            return new UserAttributesModel(doc.getId(), doc.getRole(), doc.getDepartment(),
                    doc.getTimeSchedule(), doc.getYearOfExperience(), doc.getClearanceLevel(),
                    doc.getEmploymentStatus());
        });

        // Execute
        List<UserAttributesModel> actualModels = service.getAllUsersAttributes();

        // Verify
        assertThat(actualModels).isEqualTo(expectedModels);
        verify(repository).findAll();
        verify(userMapper, times(documents.size())).toModel(any());
    }

    @Test
    void createNewUserAttributes_valid() {
        UserAttributesModel userModel = new UserAttributesModel("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Secret", "Full-Time");

        when(userMapper.toDocument(userModel)).thenReturn(new UserAttributes("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Secret", "Full-Time"));

        service.createNewUserAttributes(userModel);

        verify(userMapper).toDocument(userModel);
        verify(repository).insert(any(UserAttributes.class));
    }


    @Test
    void updateUserAttributes_userFound() {
        String userId = "1";
        UserAttributesModel userModel = new UserAttributesModel("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Secret", "Full-Time");

        UserAttributes existingDocument = new UserAttributes("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Secret", "Full-Time");
        when(repository.findById(userId)).thenReturn(Optional.of(existingDocument));
        when(userMapper.toDocument(userModel)).thenReturn(existingDocument);

        service.updateUserAttributes(userModel, userId);

        verify(repository).findById(userId);
        verify(userMapper).toModel(existingDocument);
        verify(userMapper).toDocument(userModel);
        verify(repository).save(any(UserAttributes.class));
    }


    @Test
    void deleteUserAttributes_userFound() {
        String userId = "1";
        UserAttributes existingDocument = new UserAttributes("1", "Developer", "Engineering",
                new TimeSchedule(LocalTime.of(9, 0), LocalTime.of(17, 0),
                        Set.of("MONDAY")),
                5, "Secret", "Full-Time");
        when(repository.findById(userId)).thenReturn(Optional.of(existingDocument));

        service.deleteUserAttributes(userId);

        verify(repository).findById(userId);
        verify(userMapper).toModel(existingDocument);
        verify(repository).deleteById(userId);
    }
}

