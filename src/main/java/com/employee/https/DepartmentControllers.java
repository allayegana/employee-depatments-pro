package com.employee.https;


import com.employee.Models.Department;
import com.employee.UseCase.DepartmentsService;
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
public class DepartmentControllers implements Serializable {

    @Autowired
    private DepartmentsService service;

    @GetMapping("/dpt")
    @ResponseStatus(HttpStatus.OK)
    public List<Department> findAllDpt() {
        log.info("***** GET ALL DPT CONTROLLER ***** ");
        return service.findDpt();
    }

    @PostMapping("/add/dpt")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDpt(@RequestBody Department department) {
        log.info("***** ADD NEW DPT CONTROLLER *****");
        service.creatDpt(department);
    }

    @GetMapping("/dpt/{id}")
    public Optional<Department> getcarById(@PathVariable("id") Long id) {
        log.info("FIND DPT BY ID CONTROLLER " + id);
        return service.getDptById(id);
    }

    @DeleteMapping("/dpt/{id}")
    public void removeCar(@PathVariable("id") Long id) {
        service.removeDepat(id);
        log.info("***** REMOVE DPT BY ID CONTROLLER ******" + id);

    }

    @PutMapping(path = "/update/dpt/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateDpt(@PathVariable("id") Long id, @RequestBody Department department) {
        service.updateDpart(id, department);
        log.info("***** UPDATE DPT BY ID CONTROLLER ***** " + department);
    }

}
