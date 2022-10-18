package com.rydzwr.repository;

import com.rydzwr.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    boolean existsByEmail(String email);
    User save(User user);
    void deleteAll();
    boolean existsByName(String name);
    User getUserByName(String name);
    User getUserByUserSessionCode(String sessionCode);
    boolean existsByUserSessionCode(String sessionCode);
}
