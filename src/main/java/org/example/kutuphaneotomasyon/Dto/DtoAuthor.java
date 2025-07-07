package org.example.kutuphaneotomasyon.Dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DtoAuthor {
    private Integer id;

    private String ad;


    private String soyad;
}
