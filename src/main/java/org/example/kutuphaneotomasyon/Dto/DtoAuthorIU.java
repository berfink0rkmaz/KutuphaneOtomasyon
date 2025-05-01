package org.example.kutuphaneotomasyon.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Yazar ekleme veya güncelleme işlemleri için DTO")
public class DtoAuthorIU {

    @Schema(description = "Yazarın adı", example = "Fyodor")
    private String ad;

    @Schema(description = "Yazarın soyadı", example = "Dostoyevski")
    private String soyad;


}