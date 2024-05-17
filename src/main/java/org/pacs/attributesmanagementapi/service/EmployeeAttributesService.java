package org.pacs.attributesmanagementapi.service;

import org.pacs.attributesmanagementapi.mapper.EmployeeAttributesMapper;
import org.pacs.attributesmanagementapi.model.EmployeeAttributesModel;
import org.pacs.attributesmanagementapi.repo.EmployeeAttributesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeAttributesService {
    private final EmployeeAttributesRepository repository;
    private final EmployeeAttributesMapper Mapper;

    public List<EmployeeAttributesModel> getAllEmployeesAttributes(){

        return repository.findAll()
                .stream()
                .map(Mapper::toModel)
                .collect(Collectors.toList());

    }

    public void createNewEmployeeAttributes(@Valid EmployeeAttributesModel employeeModel){
        repository.save(Mapper.toDocument(employeeModel));
    }

    public void updateEmployeeAttributes(@Valid EmployeeAttributesModel employeeModel, String employeeId) {
        if(!employeeId.equals(employeeModel.getId())) throw new ValidationException("The Path ID and Request ID not matching");
        Mapper.toModel(repository
                .findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("The Employee with ID : (" + employeeId + ") does not exist")));

        repository.save(Mapper.toDocument(employeeModel));
    }

    public void deleteEmployeeAttributes(String employeeId) {
        Mapper.toModel(repository
                .findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("The Employee with ID : (" + employeeId + ") does not exist")));

        repository.deleteById(employeeId);
    }

    public EmployeeAttributesModel findEmployeeAttributes(String employeeId) {
        return Mapper.toModel(repository
                .findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("The Employee with ID : (" + employeeId + ") does not exist")));
    }
}
