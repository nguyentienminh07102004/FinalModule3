package com.example.demo.Repository.IMPL;

import com.example.demo.Model.Entity.UserEntity;
import com.example.demo.Repository.DAO.IMPL.AbstractDAO;
import com.example.demo.Repository.IUserRepository;

public class UserRepositoryImpl extends AbstractDAO<UserEntity> implements IUserRepository {
    @Override
    public Boolean login(String username, String password) {
        return true;
    }
}
