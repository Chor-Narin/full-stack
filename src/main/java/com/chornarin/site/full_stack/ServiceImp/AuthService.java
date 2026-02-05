package com.chornarin.site.full_stack.ServiceImp;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.chornarin.site.full_stack.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private static final int MAX_FAILED_ATTEMPTS  = 5;

    public void recordFailedLogin(String email){
        userRepository.findByEmail(email).ifPresent(user-> {
                int attempts = user.getFailedLoginAttempts() + 1;
                user.setFailedLoginAttempts(attempts);

                if(attempts >= MAX_FAILED_ATTEMPTS){
                    user.setAccountLocked(true);
                    user.setLockTime(Instant.now());
                }
                userRepository.save(user);

            });
    }


    public void recordSuccessfulLogin(String email){
        userRepository.findByEmail(email)
            .ifPresent(user -> {
                user.setFailedLoginAttempts(0);
                user.setAccountLocked(false);
                userRepository.save(user);
            });

    }


}
