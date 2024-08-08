package com.example.demo.Convertor;

import com.example.demo.Model.Entity.AuthorEntity;
import com.example.demo.Model.Response.AuthorResponse;

public class AuthorConvertor {
    public static AuthorResponse toAuthorResponse(AuthorEntity author) {
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setAddress(author.getAddress());
        response.setDateOfBirth(author.getDateOfBirth());
        response.setFullName(author.getFirstname() + " " + author.getLastname());
        return response;
    }
}
