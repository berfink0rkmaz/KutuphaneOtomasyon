package org.example.kutuphaneotomasyon.Controller;

import org.example.kutuphaneotomasyon.Dto.DtoAuthorIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

public interface IAuthorController {
    GenericResponse<?> saveAuthor(DtoAuthorIU dto);
    GenericResponse<?> getAllAuthors();
    GenericResponse<?> getAuthorById(Integer id);
    GenericResponse<?> updateAuthor(Integer id, DtoAuthorIU dto);
    GenericResponse<?> deleteAuthor(Integer id);
    GenericResponse<?> getBooksByAuthorId(Integer authorId);
}
