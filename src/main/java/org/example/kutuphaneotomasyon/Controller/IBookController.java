package org.example.kutuphaneotomasyon.Controller;
import org.example.kutuphaneotomasyon.Dto.DtoBookIU;


import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IBookController {
    GenericResponse<?> saveBook(DtoBookIU dto, MultipartFile file);
    GenericResponse<?>updateBook(Integer id, DtoBookIU dto, MultipartFile file);
    GenericResponse<?>deleteBook(Integer id);
    GenericResponse<?>getAllBooks();

    GenericResponse<?> findById(Integer id);
    GenericResponse<?> searchBooksByName(String keyword);



}
