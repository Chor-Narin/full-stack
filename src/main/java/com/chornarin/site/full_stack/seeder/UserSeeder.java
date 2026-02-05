package com.chornarin.site.full_stack.seeder;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.chornarin.site.full_stack.enums.RoleEnum;
import com.chornarin.site.full_stack.models.User;
import com.chornarin.site.full_stack.repository.UserRepository;

import lombok.Data;


@Component
@Data
public class UserSeeder implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // seed data 
        User user  = User
            .builder()
            .username("Raksa")
            .password("chichi")
            .email("chornarin@gmail.com")
            .role(RoleEnum.ADMIN)
            .build();

        
        userRepository.save(user);
        
    }

}
