package org.example.kutuphaneotomasyon.Service;


import org.example.kutuphaneotomasyon.Dto.DtoAuthorIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

public interface IAuthorService {
    GenericResponse<?> saveAuthor(DtoAuthorIU dto);
    GenericResponse<?> getAllAuthors();
    GenericResponse<?> getAuthorById(Integer id);
    GenericResponse<?> updateAuthor(Integer id, DtoAuthorIU dto);
    GenericResponse<?> deleteAuthor(Integer id);
    GenericResponse<?> getBooksByAuthorId(Integer authorId);
}