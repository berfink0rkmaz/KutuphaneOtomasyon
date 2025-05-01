package org.example.kutuphaneotomasyon.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Yayınevi ekleme veya güncelleme işlemleri için DTO")
public class DtoPublisherIU {
    @Schema(description = "Yayınevinin adı", example = "Can Yayınları")
    private String ad;
}
