package com.code.craft.by.shubham.codecraft.by.shubham.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyRequest {
    private String email;
    private String otp;
}