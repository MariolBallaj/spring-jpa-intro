package com.lhind.internship.jpaintro.mapper;

import com.lhind.internship.jpaintro.model.entity.Employee;
import com.lhind.internship.jpaintro.model.resource.EmployeeResource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeResource toResource(final Employee employee) {
        return new EmployeeResource(employee.getUsername(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getMiddleName()
        );
    }

    public void updateEmployee(final Employee employeeToUpdate, final EmployeeResource employee) {
        employeeToUpdate.setUsername(employee.username());
        employeeToUpdate.setFirstName(employee.firstName());
        employeeToUpdate.setLastName(employee.lastName());
        employeeToUpdate.setMiddleName(employee.middleName());
    }
}
