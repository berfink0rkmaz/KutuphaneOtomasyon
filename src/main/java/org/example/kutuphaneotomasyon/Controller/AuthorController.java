

package org.example.kutuphaneotomasyon.Controller;

import org.example.kutuphaneotomasyon.Dto.DtoAuthorIU;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/api/Author")
public class AuthorController  {

    @Autowired
    private IAuthorService authorService;

    @PostMapping("/save")

    public GenericResponse<?> saveAuthor(@RequestBody DtoAuthorIU dto) {
        return authorService.saveAuthor(dto);
    }

    @GetMapping("/listAllAuthors")

    public GenericResponse<?> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/list/{id}")

    public GenericResponse<?> getAuthorById(@PathVariable(name = "id") Integer id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/update/{id}")

    public GenericResponse<?> updateAuthor(@PathVariable(name = "id") Integer id,
                                           @RequestBody DtoAuthorIU dto) {
        return authorService.updateAuthor(id, dto);
    }

    @DeleteMapping("/delete/{id}")

    public GenericResponse<?> deleteAuthor(@PathVariable(name = "id") Integer id) {
        return authorService.deleteAuthor(id);
    }

    @GetMapping("/{authorId}/books")

    public GenericResponse<?> getBooksByAuthorId(@PathVariable(name = "authorId") Integer authorId) {
        return authorService.getBooksByAuthorId(authorId);
    }
}
