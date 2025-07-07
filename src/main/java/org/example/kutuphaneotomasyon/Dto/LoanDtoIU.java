package org.example.kutuphaneotomasyon.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ödünç alma kaydı oluşturmak veya güncellemek için DTO")
public class LoanDtoIU {

    @Schema(description = "Kullanıcı ID", example = "1")
    private Integer userId;

    @Schema(description = "Kitap ID", example = "10")
    private Integer bookId;

    @Schema(description = "Ödünç alma tarihi", example = "2025-05-02")
    private LocalDate borrowDate;

    @Schema(description = "İade tarihi", example = "2025-05-15")
    private LocalDate returnDate;

    @Schema(description = "İade edildi mi", example = "false")
    private boolean isReturned;
}