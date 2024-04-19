package com.employee.UseCase;


import com.employee.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var userName = repository.findByUserName(username);

        User.UserBuilder userBuilder = null;

        if (userName.isPresent()) {
            com.employee.Models.User cureentUser = userName.get();

            userBuilder = User.withUsername(username);
            userBuilder.password(cureentUser.getPassword());
            userBuilder.roles(cureentUser.getRole());
            return userBuilder.build();

        } else {
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
    }
}
