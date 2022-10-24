package com.rydzwr.adapter;

import com.rydzwr.model.User;
import com.rydzwr.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer> {

}
