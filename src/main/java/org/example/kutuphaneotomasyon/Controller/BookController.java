
package org.example.kutuphaneotomasyon.Controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.example.kutuphaneotomasyon.Dto.DtoAuthorIU;
import org.example.kutuphaneotomasyon.Dto.DtoBookIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;

import org.example.kutuphaneotomasyon.Service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("rest/api/Book")
public class BookController  {
    @Autowired
    private IBookService bookService;

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponse<?> saveBook(
            @ModelAttribute DtoBookIU dto,
            @RequestParam("file") MultipartFile file
    ) {
        return bookService.saveBook(dto, file);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponse<?> updateBook(
            @PathVariable Integer id,
            @ModelAttribute DtoBookIU dto,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        return bookService.updateBook(id, dto, file);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @DeleteMapping("/delete/{id}")
    public GenericResponse<?> deleteBook(@PathVariable(name="id")Integer id) {
        return bookService.deleteBook(id);
    }

    @GetMapping(path ="/listAll")
    public GenericResponse<?> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path ="/list/{id}")
    public GenericResponse<?> findById(@PathVariable(name="id") Integer id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public GenericResponse<?> searchBooksByName(@RequestParam String keyword) {
        return bookService.searchBooksByName(keyword);
    }

    @GetMapping("/system/status")
    public GenericResponse<?> getSystemStatus() {
        return bookService.getSystemStatus();
    }



}
