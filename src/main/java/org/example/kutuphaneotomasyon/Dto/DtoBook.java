package org.example.kutuphaneotomasyon.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kutuphaneotomasyon.Entity.Durum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoBook {
    private Integer id;
    private String isbn;
    private String ad;
    private int baskiYili;
    private Durum durum;
    private String kitapKapakfotosuUrl;
    private String dil;

    private String authorAd;
    private String authorSoyad;

    private String publisherAd;

    private String categoryAd;
}
