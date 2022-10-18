package com.rydzwr.repository;

import com.rydzwr.model.User;

public interface UserRepository {
    User save(User user);
    void deleteAll();
    boolean existsByName(String name);
    User getUserByName(String name);
    User getUserByUserSessionCode(String sessionCode);
    boolean existsByUserSessionCode(String sessionCode);
}
