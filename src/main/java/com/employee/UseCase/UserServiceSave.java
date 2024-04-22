package com.employee.UseCase;


import com.employee.Models.User;
import com.employee.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceSave {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addNewUser(User user) throws Exception {
        String encoPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoPassword);
        repository.save(user);
    }

    public List<User> getAllUser() {
        Sort sort = Sort.by("userName").ascending();
      return repository.findAll(sort);
    }
}
