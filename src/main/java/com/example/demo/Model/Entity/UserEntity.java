package com.example.demo.Model.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
}
