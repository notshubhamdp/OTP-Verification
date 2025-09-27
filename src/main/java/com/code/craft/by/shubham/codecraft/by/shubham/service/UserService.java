package com.code.craft.by.shubham.codecraft.by.shubham.service;

import com.code.craft.by.shubham.codecraft.by.shubham.model.Users;
import com.code.craft.by.shubham.codecraft.by.shubham.requests.RegisterRequest;
import com.code.craft.by.shubham.codecraft.by.shubham.responses.RegisterResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest);


    void verify(String email, String otp);
}
