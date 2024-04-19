package com.employee.UseCase;


import com.employee.Models.User;
import com.employee.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceSave {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void addNewUser(User user) throws Exception {
        String password = user.getPassword();
        String encoPassword = passwordEncoder.encode(password);
        user.setPassword(encoPassword);
        repository.save(user);
    }
}
