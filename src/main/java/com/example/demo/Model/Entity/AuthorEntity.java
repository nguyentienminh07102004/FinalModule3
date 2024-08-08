package com.example.demo.Model.Entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AuthorEntity {
    private Long id;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String address;
}
