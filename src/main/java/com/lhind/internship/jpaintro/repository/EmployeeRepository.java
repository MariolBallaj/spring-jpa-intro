package com.lhind.internship.jpaintro.repository;

import com.lhind.internship.jpaintro.model.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    List<Employee> findByFirstNameOrderByUsernameDesc(final String firstName);

    List<Employee> findByFirstName(final String firstName, final Sort sort);

    List<Employee> findByFirstName(final String firstName, final Pageable pageable);

    List<Employee> findByFirstNameAndLastName(final String firstName, final String lastName);

    Optional<Employee> findByUsername(final String username);

    List<Employee> findByUsernameLike(final String username);

    List<Employee> findByUsernameStartsWith(final String username);

    List<Employee> findByUsernameEndsWithOrFirstNameStartsWith(final String username, final String firstName);

    List<Employee> findByEmployeeDetailsEmploymentDateBetween(final Date employmentStartDate, final Date employmentEndDate);

    List<Employee> findByMiddleNameIsNull();

    @Query("SELECT e FROM Employee AS e WHERE e.username = :username")
    List<Employee> findByQuery(@Param("username") final String username);

    @NativeQuery("SELECT * FROM employee WHERE username = :username")
    List<Employee> findByQueryNative(@Param("username") final String username);
}
