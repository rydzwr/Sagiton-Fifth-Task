package com.rydzwr.repository;

import com.rydzwr.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User save(User user);
    void deleteAll();
    User findByUsername(String username);
}
