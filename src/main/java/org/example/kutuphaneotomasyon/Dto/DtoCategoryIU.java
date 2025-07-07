package org.example.kutuphaneotomasyon.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Kategori ekleme veya güncelleme işlemleri için DTO")
public class DtoCategoryIU {
    @Schema(description = "Kategori adı", example = "Roman")
    private String ad;
}
