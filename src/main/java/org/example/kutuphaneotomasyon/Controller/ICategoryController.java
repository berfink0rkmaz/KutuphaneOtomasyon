package org.example.kutuphaneotomasyon.Controller;


import org.example.kutuphaneotomasyon.Dto.DtoCategoryIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

public interface ICategoryController {
    GenericResponse<?> saveCategory(DtoCategoryIU dto);
    GenericResponse<?> getAllCategories();
    GenericResponse<?> getCategoryById(Integer id);
    GenericResponse<?> updateCategory(Integer id, DtoCategoryIU dto);
    GenericResponse<?> deleteCategory(Integer id);
    GenericResponse<?> getBooksByCategoryId(Integer categoryId);
}
