package com.rydzwr.model;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String userSessionCode;
    private String role;

    public User(String name, String surname, String email, String password, String userSessionCode, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userSessionCode = userSessionCode;
        this.role = role;
    }
}
