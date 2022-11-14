package com.rydzwr.repository;

import com.rydzwr.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User save(User user);
    void deleteAll();
    User findByUsername(String username);
}
