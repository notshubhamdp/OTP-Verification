package com.code.craft.by.shubham.codecraft.by.shubham.service.IMPL;


import com.code.craft.by.shubham.codecraft.by.shubham.model.Users;
import com.code.craft.by.shubham.codecraft.by.shubham.repository.UserRepository;
import com.code.craft.by.shubham.codecraft.by.shubham.requests.RegisterRequest;
import com.code.craft.by.shubham.codecraft.by.shubham.responses.RegisterResponse;
import com.code.craft.by.shubham.codecraft.by.shubham.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;



@RequiredArgsConstructor
@Service

public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;


    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        Users existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser != null) {
            // User with the same email already exists
            throw new RuntimeException("User already Registered");
        }
        Users users = Users.builder()
                .userName(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
    String otp = generateOTP();
    users.setOtp(otp);
    Users savedUsers = userRepository.save(users);
        sendVarificationEmail(savedUsers.getEmail(),otp);
        RegisterResponse response = RegisterResponse.builder()
                .username(users.getUserName())
                .email(users.getEmail())
                .build();
        return response;
    }

    @Override
    public void verify(String email, String otp) {
        Users users = userRepository.findByEmail(email);
        if (users == null) {
            throw new RuntimeException("User not found");
        } else if (users.isVerified()) {
            throw new RuntimeException("User already verified");
        } else if (otp.equals(users.getOtp())) {
            users.setVerified(true);
            users.setOtp(null); // Clear OTP after successful verification
            userRepository.save(users);
        } else {
            throw new RuntimeException("Internal Server Error");

        }
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP
        return String.valueOf(otp);
    }

    private void sendVarificationEmail(String email, String otp){
        // Send email logic
        String subject = "Email Verification";
        String body = "Your OTP for email verification is: " + otp;
        emailService.sendEmail(email, subject, body);
    }


}
