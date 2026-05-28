package com.example.employeemanagement.service;

import com.example.employeemanagement.exception.EmployeeNotFoundException;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    // Test 1: Lay danh sach nhan vien - co du lieu
    @Test
    public void getAllEmployees_ReturnList() {
        Employee e1 = new Employee(1L, "Nguyen Van A", "Engineering", 15000000.0);
        Employee e2 = new Employee(2L, "Tran Thi B", "HR", 12000000.0);
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Employee> result = employeeService.getAllEmployees();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    // Test 2: Tim nhan vien theo ID - tim thay
    @Test
    public void getById_Found() {
        Employee employee = new Employee(1L, "Nguyen Van A", "Engineering", 15000000.0);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getById(1L);

        assertNotNull(result);
        assertEquals("Nguyen Van A", result.getFullName());
    }

    // Test 3: Tim nhan vien theo ID - khong tim thay, nem ngoai le
    @Test
    public void getById_NotFound_ThrowException() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getById(99L));
    }

    // Test 4: Them nhan vien thanh cong
    @Test
    public void addEmployee_Success() {
        Employee employee = new Employee(null, "Pham Thi D", "Marketing", 11000000.0);
        Employee saved = new Employee(4L, "Pham Thi D", "Marketing", 11000000.0);
        when(employeeRepository.save(employee)).thenReturn(saved);

        Employee result = employeeService.addEmployee(employee);

        assertNotNull(result.getId());
        assertEquals("Pham Thi D", result.getFullName());
    }

    // Test 5: Xoa nhan vien dung phan tu
    @Test
    public void deleteEmployee_RemovesCorrectElement() {
        Employee employee = new Employee(1L, "Nguyen Van A", "Engineering", 15000000.0);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).delete(employee);
    }
}
