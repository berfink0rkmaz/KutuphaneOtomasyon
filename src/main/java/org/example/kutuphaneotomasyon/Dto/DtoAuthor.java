package org.example.kutuphaneotomasyon.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DtoAuthor {
    private Integer id;

    private String ad;


    private String soyad;
}
