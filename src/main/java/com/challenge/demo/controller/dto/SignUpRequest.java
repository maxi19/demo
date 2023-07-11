package com.challenge.demo.controller.dto;

import com.challenge.demo.entity.User;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validated
@Data
public class SignUpRequest {

    @Nullable
    private String name;

    @NotEmpty(message = "email is mandatory")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",  message = "Debe incresar un mail valido")
    private String email;

    @NotEmpty(message = "password is mandatory")
    @Size(min = 8, message = "password miniom 8 caracteres")
    @Size(max = 12, message = "password maximo 12 caracteres")
    private String password;

    public User toEntity(){
        return new User(getName(),getEmail(),getPassword(),false);
    }

}
