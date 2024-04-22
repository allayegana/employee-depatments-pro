package com.employee.https;


import com.employee.Models.User;
import com.employee.UseCase.UserServiceSave;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("api/v1")
public class UsersControllers {


    @Autowired
    private UserServiceSave serviceSave;

    @PostMapping("/add/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDpt(@RequestBody User user) throws Exception {
        log.info("***** ADD NEW DPT CONTROLLER *****");
        serviceSave.addNewUser(user);
    }


    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUser() {
        log.info("***** GET ALL USERS CONTROLLER *****");
        return serviceSave.getAllUser();
    }

}
