package com.lhind.internship.jpaintro.service;

import com.lhind.internship.jpaintro.mapper.EmployeeMapper;
import com.lhind.internship.jpaintro.model.resource.EmployeeResource;
import com.lhind.internship.jpaintro.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(final EmployeeRepository employeeRepository, final EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeResource> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toResource)
                .toList();
    }

    public Optional<EmployeeResource> getById(final Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toResource);
    }

    public void save(final Long employeeId, final EmployeeResource employeeResource) {
        employeeRepository.findById(employeeId).ifPresent(employee -> {
            employeeMapper.updateEmployee(employee, employeeResource);
            employeeRepository.save(employee);
        });
    }
}
