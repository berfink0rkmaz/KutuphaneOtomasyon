package org.example.kutuphaneotomasyon.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoIU {
    private String username;
    private String email;
    private String password; // şifre hashlenecek
}
