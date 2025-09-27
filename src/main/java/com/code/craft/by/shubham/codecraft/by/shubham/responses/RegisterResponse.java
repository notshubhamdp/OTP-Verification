package com.code.craft.by.shubham.codecraft.by.shubham.responses;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RegisterResponse {

    private String username;
    private String email;
}
