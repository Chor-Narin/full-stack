package com.chornarin.site.full_stack.controllers.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chornarin.site.full_stack.ServiceImp.StudentServiceImp;
import com.chornarin.site.full_stack.ServiceImp.UserServiceImp;
import com.chornarin.site.full_stack.dto.requests.LoginRequest;
import com.chornarin.site.full_stack.dto.requests.RegisterRequest;
import com.chornarin.site.full_stack.dto.responses.AuthResponse;

import jakarta.annotation.security.PermitAll;
import lombok.Data;

@RestController
@RequestMapping(UserController.BASE_URL)
@Data
public class UserController {


    public static final String BASE_URL = "/api/auth";

    private final UserServiceImp userServiceImp;
    private final StudentServiceImp studentServiceImp;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @Validated @RequestBody RegisterRequest request) {
        
        userServiceImp.register(request);
        return ResponseEntity.ok("user created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Validated @RequestBody LoginRequest request){
        AuthResponse response =  userServiceImp.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    @PermitAll
    public  ResponseEntity<?> getALLUser(){
        return ResponseEntity.ok(userServiceImp.getUser());
    }

    @GetMapping("/student")
    public ResponseEntity<?> getAllStudent(){
        return ResponseEntity.ok(studentServiceImp.getAll());
    }

    // just for testing
    @GetMapping
    public String returnMessage() {
        return "what the fuck";
    }
}