package com.chornarin.site.full_stack.services;

import java.util.List;

import javax.security.auth.login.AccountLockedException;

import com.chornarin.site.full_stack.dto.requests.LoginRequest;
import com.chornarin.site.full_stack.dto.requests.RegisterRequest;
import com.chornarin.site.full_stack.dto.responses.AuthResponse;
import com.chornarin.site.full_stack.models.User;

public interface UserService {

    Boolean findByEmail(String email);

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest requestDto) throws AccountLockedException ;

    List<User> getAllUsers();

}
