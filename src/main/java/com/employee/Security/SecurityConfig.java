package com.employee.Security;


import com.employee.UseCase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService service;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable).
                httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests((c) -> {
                    c.anyRequest().authenticated();
                }).build();

    }

    @Autowired
    public void configureGloba(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("ALLAYE")
//                .password(bCryptPasswordEncoder().encode("GANA123"))
//                .roles("USER")
//                .build();
//
//
//        UserDetails user1 = User.builder()
//                .username("USER")
//                .password(bCryptPasswordEncoder().encode("PASSWORD"))
//                .roles("ADMI")
//                .build();
//        return new InMemoryUserDetailsManager(user, user1);
//    }


}
