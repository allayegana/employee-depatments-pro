package com.employee;


import com.employee.Models.Department;
import com.employee.Models.Employee;
import com.employee.UseCase.EmployeeService;
import com.employee.https.EmployeeControllers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeesControllersTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeControllers controllers;


    // get employee and department
    @Test
    void getEmployeeById() {
        when(employeeService.getEmployee(1L))
                .thenReturn(Optional.of(new Employee(1L, "ALLAYE", "GANA", "456123", 78999999,
                        new Department(1L, "JAVA"))));

        var getbyIdEmp = controllers.getEmpById(1L);
        assertTrue(getbyIdEmp.isPresent());
    }


    //add new enroll "employees and department"
    @Test
    void createEnroll() {
        var emp = new Employee(2L, "ALLAYE", "GANA", "456123", 78999999,
                new Department(2L, "JAVA"));
        doNothing().when(employeeService).enrollerNewEmp(emp);

        controllers.addNewCar(emp);
        verify(employeeService, times(1)).enrollerNewEmp(emp);

    }


    // find all employees and department
    @Test
    void findAllEmployee() {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", "123456", 7888888, new Department(1L, "IT")));
        employees.add(new Employee(2L, "Jane", "Smith", "654321", 9999999, new Department(2L, "HR")));

        when(employeeService.findEmployee()).thenReturn(employees);

        List<Employee> returnedEmployees = controllers.findAllEmp();
        assertNotNull(returnedEmployees);
        assertFalse(returnedEmployees.isEmpty());
        assertEquals(2, returnedEmployees.size());

    }


    // update em and depadtment byId
    @Test
    void updatebyId() {
        Employee employee = new Employee(1L, "ALLAYE", "GANA", "456123", 78999999,
                new Department(1L, "JAVA"));

        // Mock the behavior of employeeService.updateEmployee() to perform no action (void)
        doNothing().when(employeeService).updateEmployee(1L, employee);
        controllers.updateEmployee(1L, employee);
        verify(employeeService, times(1)).updateEmployee(1L, employee);
    }


    // remove employee and depadtment by Id
    @Test
    void deletebyId() {
        Employee employee = new Employee(1L, "ALLAYE", "GANA", "456123", 78999999,
                new Department(1L, "JAVA"));
        doNothing().when(employeeService).removeCar(1L);
        controllers.removeCar(1L);
        verify(employeeService, times(1)).removeCar(1L);
    }

}