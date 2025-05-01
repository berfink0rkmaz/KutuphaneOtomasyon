package org.example.kutuphaneotomasyon.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoSystemStatus {
    private long toplamKitap;
    private long musaitKitap;
    private long oduncVerilmisKitap;
}
