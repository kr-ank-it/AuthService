package com.ank.authservice.dtos;

import com.ank.authservice.models.Role;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignUpRequestDto {
    // private String name;
    private String email;
    private String password;

    @ManyToMany
    private List<Role> roles;
}
