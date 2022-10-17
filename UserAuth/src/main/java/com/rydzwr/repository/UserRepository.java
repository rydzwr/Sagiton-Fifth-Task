package com.rydzwr.repository;

import com.rydzwr.model.User;

public interface UserRepository {
    boolean existsByEmail(String email);
    User save(User user);
    void deleteAll();
    boolean existsByName(String name);
    User getUserByName(String name);
}
