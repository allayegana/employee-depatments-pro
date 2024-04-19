package com.employee.UseCase;


import com.employee.EmployeeException;
import com.employee.Models.Department;
import com.employee.Repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentsService {
    @Autowired
    private DepartmentRepository repository;

    public void creatDpt(Department department) {
        log.info("ADD NEW DPT");
        repository.save(department);
    }

    public List<Department> findDpt() {
        log.info("***** GET DPT ALL SERVICE *****");
        Sort sort = Sort.by("id").descending();

        return repository.findAll(sort);
    }

    public Optional<Department> getDptById(Long id) {
        var owner = repository.findById(id);

        log.info("***** GET DPT BY ID SERVICE ****** ");
        if (owner.isEmpty()) {
            throw new EmployeeException(" you no have this DPT for this owner");
        }

        return owner;
    }

    public void removeDepat(Long id) {
        log.info("***** REMOVE DPT BY ID SERVICE ****** " + id);
        if (id == null) {
            throw new EmployeeException("NOT FIND THIS DPT WITH THIS ID " + id);
        }
        repository.deleteById(id);
    }

    public void updateDpart(Long id, Department department) {

        log.info("***** UPDATE DPT BY ID SERVICE ****** " + department);
        Optional<Department> currentUser = repository.findById(id);
        BeanUtils.copyProperties(department, currentUser);
        repository.save(department);
    }
}
