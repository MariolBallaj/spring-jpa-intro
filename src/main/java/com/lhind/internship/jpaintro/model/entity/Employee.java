package com.lhind.internship.jpaintro.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
        @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id")
})
@Entity
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(name = "unique_username_constraint", columnNames = {"username"})
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmployeeDetails employeeDetails;

    @OneToMany(mappedBy = "employee")
    private List<Booking> bookings;

    @ManyToMany(mappedBy = "employees")
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(final EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(final List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }
}
