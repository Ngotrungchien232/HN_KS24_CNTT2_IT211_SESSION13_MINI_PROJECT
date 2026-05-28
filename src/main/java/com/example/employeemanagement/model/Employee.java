package com.example.employeemanagement.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "employees")
@JacksonXmlRootElement(localName = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ho ten khong duoc de trong")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "Phong ban khong duoc de trong")
    @Column(name = "department", nullable = false)
    private String department;

    @Positive(message = "Luong phai lon hon 0")
    @Column(name = "salary", nullable = false)
    private Double salary;

    public Employee() {}

    public Employee(Long id, String fullName, String department, Double salary) {
        this.id = id;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", fullName='" + fullName + "', department='" + department + "', salary=" + salary + "}";
    }
}
