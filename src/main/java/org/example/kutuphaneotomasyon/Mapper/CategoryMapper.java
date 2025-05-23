package org.example.kutuphaneotomasyon.Mapper;

import org.example.kutuphaneotomasyon.Dto.DtoCategory;
import org.example.kutuphaneotomasyon.Dto.DtoCategoryIU;
import org.example.kutuphaneotomasyon.Entity.Category;
import org.springframework.stereotype.Component;


public class CategoryMapper {

    public static Category dtoToCategory(DtoCategoryIU dto) {
        if (dto == null) return null;

        Category category = new Category();
        category.setAd(dto.getAd());
        return category;
    }

    public static DtoCategory categoryToDto(Category category) {
        if (category == null) return null;

        DtoCategory dto = new DtoCategory();
        dto.setId(category.getId());
        dto.setAd(category.getAd());
        return dto;
    }

    public static void updateCategoryFromDto(DtoCategoryIU dto, Category category) {
        if (dto == null || category == null) return;

        if (dto.getAd() != null) {
            category.setAd(dto.getAd());
        }
    }
}
