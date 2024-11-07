package com.example.demo.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class UserCreateDTO {

    private Long userId;
    private String email;
    private String password;
    private String userName;
    private Boolean disabled;

    public UserCreateDTO(String email, String password, String userName, Boolean disabled) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.disabled = disabled;
    }

}
