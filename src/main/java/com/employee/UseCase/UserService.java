package com.employee.UseCase;


import com.employee.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

//    @Override
//    public List<ProductDto> findByName(String name) {
//        if (name.equalsIgnoreCase("ADMIN")) {
//            List<Product> products = productRepository.findAll();
//            List<ProductDto> productDtos = new ArrayList<>();
//            for (Product product : products) {
//                ProductDto productDto = convertToDto(product);
//                productDtos.add(productDto);
//            }
//            return productDtos;
//        } else {
//            throw new RuntimeException("Invalid name provided: " + name);
//        }
//    }
//
//    private ProductDto convertToDto(Product product) {
//        ProductDto dto = new ProductDto();
//
//        dto.setId(product.getId());
//        dto.setName(product.getName());
//        dto.setPrice(product.getPrice());
//        dto.setCategory(product.getCategory());
//
//        return dto;
//    }
}
