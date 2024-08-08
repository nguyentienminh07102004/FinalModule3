package com.example.demo.Model.Response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AuthorResponse {
    private Long id;
    private String fullName;
    private String address;
    private Date dateOfBirth;
}
