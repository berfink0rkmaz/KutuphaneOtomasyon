package org.example.kutuphaneotomasyon.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kutuphaneotomasyon.Entity.Durum;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {

    private Integer id;

    private Integer userId;
    private Integer bookId;

    private Durum durum;

    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;

    private String userName;   // Optional: gösterim için
    private String bookTitle;  // Optional: gösterim için
}