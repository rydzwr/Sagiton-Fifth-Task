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
    boolean existsByName(String name);
    User getUserByName(String name);
    User getUserByUserSessionCode(String sessionCode);
    boolean existsByUserSessionCode(String sessionCode);

    @Modifying
    @Query("update User set userSessionCode = null where userSessionCode = :userSessionCode")
    void clearUserSession(@Param("userSessionCode") String userSessionCode);

    @Query(value = "SELECT EXISTS(SELECT * FROM users where name = :name and password = :password)", nativeQuery = true)
    int checkPassword(@Param("name") String name, @Param("password") String password);

    @Modifying
    @Query("update User set userSessionCode = :sessionCode where name = :name")
    void updateSessionCode(@Param("name") String name, @Param("sessionCode") String sessionCode);
}
