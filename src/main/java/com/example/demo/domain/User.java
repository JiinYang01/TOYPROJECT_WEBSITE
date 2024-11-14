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

    // 역할 필드 추가
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public User(String email, String password, String userName, Boolean disabled, List<String> roles) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.disabled= disabled;
        this.roles = roles;
    }

    public User(String email, String encode, String userName, Boolean disabled) {
    }

    //    public Collection<Object> getRoles() {
//        return java.util.List.of();
//    }
    // roles 필드를 사용하여 역할 반환
    public Collection<String> getRoles() {
        return Collections.singleton(roles.toString());
    }
}
