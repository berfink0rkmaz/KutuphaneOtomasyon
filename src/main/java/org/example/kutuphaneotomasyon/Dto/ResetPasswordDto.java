package org.example.kutuphaneotomasyon.Dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String email;
    private String verificationCode;
    private String newPassword;
}