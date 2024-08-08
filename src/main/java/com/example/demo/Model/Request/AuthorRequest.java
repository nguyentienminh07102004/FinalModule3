package com.example.demo.Model.Request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AuthorRequest extends BaseRequest {
    private String fullName;
    private String address;
    private Date dateOfBirth;
}
