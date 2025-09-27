package com.code.craft.by.shubham.codecraft.by.shubham.controller;


import com.code.craft.by.shubham.codecraft.by.shubham.requests.RegisterRequest;
import com.code.craft.by.shubham.codecraft.by.shubham.requests.VerifyRequest;
import com.code.craft.by.shubham.codecraft.by.shubham.responses.RegisterResponse;
import com.code.craft.by.shubham.codecraft.by.shubham.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {


    public final UserService userService;



    @PostMapping("/register")
        public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest){
           //System.out.println("Received RegisterRequest: username=" + registerRequest.getUsername() + ", email=" + registerRequest.getEmail());
            RegisterResponse response = userService.register(registerRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }


    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyRequest verifyRequest){
        try {
            userService.verify(verifyRequest.getEmail(), verifyRequest.getOtp());
            return new ResponseEntity<>("User verified successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
