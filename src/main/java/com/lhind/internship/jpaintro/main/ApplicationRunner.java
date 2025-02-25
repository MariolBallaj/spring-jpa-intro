package com.lhind.internship.jpaintro.main;

import com.lhind.internship.jpaintro.model.entity.Employee;
import com.lhind.internship.jpaintro.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements InitializingBean {

    private final EmployeeRepository employeeRepository;

    public ApplicationRunner(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public void afterPropertiesSet() {
        // Method testing here
    }

    // Specification executor example
    private Specification<Employee> getSpecification() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), "mariol.ballaj");
    }
}
