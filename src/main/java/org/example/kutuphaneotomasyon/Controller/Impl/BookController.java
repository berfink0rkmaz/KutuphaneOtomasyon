package org.example.kutuphaneotomasyon.Controller.Impl;

import org.example.kutuphaneotomasyon.Controller.IBookController;
import org.example.kutuphaneotomasyon.Dto.DtoAuthorIU;
import org.example.kutuphaneotomasyon.Dto.DtoBookIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

import org.example.kutuphaneotomasyon.Service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin
@RestController
@RequestMapping("rest/api/Book")
public class BookController implements IBookController {
    @Autowired
    private IBookService bookService;
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Override
    public GenericResponse<?> saveBook(
            @ModelAttribute DtoBookIU dto,
            @RequestParam("file") MultipartFile file
    ) {
        return bookService.saveBook(dto, file);
    }


    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponse<?> updateBook(
            @PathVariable Integer id,
            @ModelAttribute DtoBookIU dto,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        return bookService.updateBook(id, dto, file);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public GenericResponse<?> deleteBook(@PathVariable(name="id")Integer id) {
        return bookService.deleteBook(id);
    }
    @GetMapping(path ="/listAll")
    @Override
    public GenericResponse<?> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path ="/list/{id}")
    @Override
    public GenericResponse<?> findById(@PathVariable(name="id") Integer id) {
        return bookService.findById(id);
    }
    @GetMapping("/search")
    @Override
    public GenericResponse<?> searchBooksByName(@RequestParam String keyword) {
        return bookService.searchBooksByName(keyword);
    }
    @GetMapping("/system/status")
    public GenericResponse<?> getSystemStatus() {
        return bookService.getSystemStatus();
    }



}
