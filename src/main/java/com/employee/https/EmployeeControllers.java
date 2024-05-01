package com.employee.https;


import com.employee.Models.Employee;
import com.employee.UseCase.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin("localhost:3000")
public class EmployeeControllers implements Serializable {

    @Autowired
    private EmployeeService service;

    @GetMapping("/emp")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> findAllEmp() {
        log.info("***** GET ALL EMP AND DEPARTMENTS CONTROLLER ***** ");
        return service.findEmployee();
    }

    @PostMapping("/add/emp")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewCar(@RequestBody Employee employee) {
        log.info("***** ADD NEW EMP CONTROLLER ***** ");
        service.enrollerNewEmp(employee);
    }


   // @Secured("USER")
    @GetMapping("/emp/{id}")
    public Optional<Employee> getEmpById(@PathVariable("id") Long id) {
        log.info(" ***** FIND EMP AND DEPARTMENTS BY ID CONTROLLER ***** id= " + id);
        return service.getEmployee(id);
    }

    @DeleteMapping("/emp/{id}")
    public void removeCar(@PathVariable("id") Long id) {
        service.removeCar(id);
        log.info("***** REMOVE EMP AND DEPARTMENTS BY ID CONTROLLER ****** id= " + id);

    }

    @PutMapping(path = "/update/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        service.updateEmployee(id, employee);
        log.info("***** UPDATE EMP AND DEPARTMENTS BY ID CONTROLLER ***** EMPLOYEES= " + employee);
    }

//    @GetMapping("product/ad")
//    public List<ProductDto> findbyName(@RequestParam("role") String name) {
//        log.info("***** FIND PRODUCT BY REQUEST PARAM CONTROLLER *****");
//        return productService.findByName(name);
//    }

}
