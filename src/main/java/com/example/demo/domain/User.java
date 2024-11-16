package com.example.demo.domain;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String email;
    private String password;
    private String userName;
    private Boolean disabled;

    public User(String email, String password, String userName, Boolean disabled) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.disabled= disabled;
    }

}
