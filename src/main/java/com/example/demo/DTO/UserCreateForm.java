package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class UserCreateForm {
    @Email
    @NotEmpty(message = "필수항목입니다.")
    private String email;
    @NotEmpty(message = "필수항목입니다.")
    private String password1;
    @NotEmpty(message = "필수항목입니다.")
    private String password2;
    @NotEmpty(message = "필수항목입니다.")
    @Size(min = 3, max = 25)
    private String userName;
    private Boolean disabled;
}
