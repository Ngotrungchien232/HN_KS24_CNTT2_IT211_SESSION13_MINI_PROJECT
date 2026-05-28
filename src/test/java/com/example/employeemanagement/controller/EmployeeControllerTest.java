package com.example.employeemanagement.controller;

import com.example.employeemanagement.exception.EmployeeNotFoundException;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    // Test 1: GET /api/employees - tra ve 200 va danh sach JSON
    @Test
    public void getAllEmployees_Return200() throws Exception {
        Employee e1 = new Employee(1L, "Nguyen Van A", "Engineering", 15000000.0);
        Employee e2 = new Employee(2L, "Tran Thi B", "HR", 12000000.0);
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(e1, e2));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].fullName").value("Nguyen Van A"));
    }

    // Test 2: GET /api/employees/{id} - tim thay, tra ve 200
    @Test
    public void getById_Found_Return200() throws Exception {
        Employee employee = new Employee(1L, "Nguyen Van A", "Engineering", 15000000.0);
        when(employeeService.getById(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Nguyen Van A"));
    }

    // Test 3: GET /api/employees/{id} - khong tim thay, tra ve 404
    @Test
    public void getById_NotFound_Return404() throws Exception {
        when(employeeService.getById(99L)).thenThrow(new EmployeeNotFoundException(99L));

        mockMvc.perform(get("/api/employees/99"))
                .andExpect(status().isNotFound());
    }

    // Test 4: POST /api/employees - tao thanh cong, tra ve 201
    @Test
    public void addEmployee_Return201() throws Exception {
        Employee input = new Employee(null, "Hoang Van E", "Engineering", 18000000.0);
        Employee saved = new Employee(5L, "Hoang Van E", "Engineering", 18000000.0);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(saved);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.fullName").value("Hoang Van E"));
    }
}
