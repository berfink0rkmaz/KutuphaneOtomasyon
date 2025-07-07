package org.example.kutuphaneotomasyon.Dto;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDto {
    private String email;
    private String verificationCode;
    private String newPassword;
}