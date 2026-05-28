package com.example.employeemanagement.service;

import com.example.employeemanagement.exception.EmployeeNotFoundException;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        logger.info("Lay danh sach tat ca nhan vien");
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        logger.info("Tim nhan vien voi id: {}", id);
        return employeeRepository.findById(id).orElseThrow(() -> {
            logger.warn("Khong tim thay nhan vien voi id: {}", id);
            return new EmployeeNotFoundException(id);
        });
    }

    public Employee addEmployee(Employee employee) {
        logger.info("Them nhan vien moi: {}", employee.getFullName());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        logger.info("Cap nhat nhan vien voi id: {}", id);
        Employee existing = employeeRepository.findById(id).orElseThrow(() -> {
            logger.warn("Khong tim thay nhan vien voi id: {}", id);
            return new EmployeeNotFoundException(id);
        });
        existing.setFullName(updatedEmployee.getFullName());
        existing.setDepartment(updatedEmployee.getDepartment());
        existing.setSalary(updatedEmployee.getSalary());
        return employeeRepository.save(existing);
    }

    public void deleteEmployee(Long id) {
        logger.info("Xoa nhan vien voi id: {}", id);
        Employee existing = employeeRepository.findById(id).orElseThrow(() -> {
            logger.warn("Khong tim thay nhan vien voi id: {}", id);
            return new EmployeeNotFoundException(id);
        });
        employeeRepository.delete(existing);
    }
}
