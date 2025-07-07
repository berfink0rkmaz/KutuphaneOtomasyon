package org.example.kutuphaneotomasyon.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoSystemStatus {
    private long toplamKitap;
    private long musaitKitap;
    private long oduncVerilmisKitap;
}
