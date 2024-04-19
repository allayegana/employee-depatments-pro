package com.employee.UseCase;


import com.employee.EmployeeException;
import com.employee.Models.Employee;
import com.employee.Repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public void enrollerNewEmp(Employee employee) {
        log.info("***** enroller new employee and departments SERVICE ***** ".toUpperCase() + employee);

        Random gerador = new Random();
        for (int i = 1; i < 20; i++) {
            gerador.nextInt();
        }
        employee.setEnrollerId(gerador.nextInt(30302022) + 1);

        repository.save(employee);
    }

    public List<Employee> findEmployee() {
        log.info("***** GET ALL EMPLOYEES AND DEPARTMENTS  SERVICE *****");
        Sort sort = Sort.by("id").descending();
        return repository.findAll(sort);
    }


    public Optional<Employee> getEmployee(Long id) {
        var carId = repository.findById(id);
        log.info("***** GET EMP AND DEPARTMENT BY ID SERVICE ****** " + id);
        if (carId.isEmpty()) {
            throw new EmployeeException(" you no have this EMP your in car show".toUpperCase());
        }
        return carId;
    }


    public void updateEmployee(Long id, Employee employee) {
        log.info("***** UPDATE EMP AND DEPARTMENT BY ID SERVICE ******".toUpperCase() + employee);
        Employee currentUser = repository.findCarById(id);
        Random gerador = new Random();
        for (int i = 1; i < 20; i++) {
            gerador.nextInt();
        }
        employee.setEnrollerId(currentUser.getEnrollerId());
        BeanUtils.copyProperties(employee, currentUser);
        repository.save(employee);
    }

    public void removeCar(Long id) {
        log.info("***** REMOVE EMP AND DEPARTMENT BY ID SERVICE ****** " + id);
        if (id == null) {
            throw new EmployeeException("NOT FIND THIS EMP WITH THIS ID " + id);
        }
        repository.deleteById(id);
    }
}
