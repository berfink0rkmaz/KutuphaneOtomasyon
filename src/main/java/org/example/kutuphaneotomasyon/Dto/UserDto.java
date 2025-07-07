package org.example.kutuphaneotomasyon.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kutuphaneotomasyon.Entity.User.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
    @AllArgsConstructor
    public class UserDto {
        private Integer id;
        private String username;
        private String email;
        private Role role;
        private boolean enabled;
    }

