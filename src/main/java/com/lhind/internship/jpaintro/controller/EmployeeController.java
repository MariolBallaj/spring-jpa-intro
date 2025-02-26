package com.lhind.internship.jpaintro.controller;

import com.lhind.internship.jpaintro.model.entity.Employee;
import com.lhind.internship.jpaintro.model.resource.EmployeeResource;
import com.lhind.internship.jpaintro.repository.EmployeeRepository;
import com.lhind.internship.jpaintro.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.lhind.internship.jpaintro.controller.EmployeeController.EMPLOYEES_BASE_PATH;

@RestController
@RequestMapping(EMPLOYEES_BASE_PATH)
public class EmployeeController {

    static final String EMPLOYEES_BASE_PATH = "/employees";

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    public EmployeeController(final EmployeeRepository employeeRepository, final EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EmployeeResource>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping(path = "/{employeeId}", produces = "application/json")
    public ResponseEntity<EmployeeResource> getEmployeeById(@PathVariable("employeeId") final Long employeeId) {
        return employeeService.getById(employeeId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/search", produces = "application/json")
    public ResponseEntity<List<Employee>> getByFirstNameAndLastName(@RequestParam("firstName") final String firstName,
            @RequestParam("lastName") final String lastName) {
        return ResponseEntity.ok(employeeRepository.findByFirstNameAndLastName(firstName, lastName));
    }

    @PutMapping(path = "{employeeId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> updateEmployee(@PathVariable("employeeId") final Long employeeId, @RequestBody final EmployeeResource employee) {
        employeeService.save(employeeId, employee);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{employeeId}", produces = "application/json")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") final Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return ResponseEntity.noContent().build();
    }

}
