package org.example.kutuphaneotomasyon.Service.Impl;

import org.example.kutuphaneotomasyon.Dto.DtoAuthor;
import org.example.kutuphaneotomasyon.Dto.DtoAuthorIU;
import org.example.kutuphaneotomasyon.Dto.DtoBook;
import org.example.kutuphaneotomasyon.Entity.Author;
import org.example.kutuphaneotomasyon.Entity.Book;
import org.example.kutuphaneotomasyon.Mapper.AuthorMapper;
import org.example.kutuphaneotomasyon.Mapper.BookMapperView;
import org.example.kutuphaneotomasyon.Repository.AuthorRepository;
import org.example.kutuphaneotomasyon.Repository.BookRepository;
import org.example.kutuphaneotomasyon.ResponseMessage.Constants;
import org.example.kutuphaneotomasyon.ResponseMessage.GenericResponse;
import org.example.kutuphaneotomasyon.Service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorMapper authorMapper;

    private final BookMapperView bookMapperView = new BookMapperView();

    @Override
    public GenericResponse<?> saveAuthor(DtoAuthorIU dto) {
        System.out.println("saveAuthor called...");

        Author author = authorMapper.dtoToAuthor(dto);
        Author saved = authorRepository.save(author);
        return GenericResponse.success(authorMapper.authorToDto(saved));
    }

    @Override
    public GenericResponse<?> getAllAuthors() {
        System.out.println("getAllAuthors called...");

        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            return GenericResponse.error(Constants.EMPTY_LIST);
        }

        List<DtoAuthor> dtoList = authors.stream()
                .map(authorMapper::authorToDto)
                .collect(Collectors.toList());

        return GenericResponse.success(dtoList);
    }

    @Override
    public GenericResponse<?> getAuthorById(Integer id) {
        System.out.println("getAuthorById called...");

        Optional<Author> optional = authorRepository.findById(id);
        return optional.map(author -> GenericResponse.success(authorMapper.authorToDto(author)))
                .orElseGet(() -> GenericResponse.error(Constants.EMPTY_ID));
    }

    @Override
    public GenericResponse<?> updateAuthor(Integer id, DtoAuthorIU dto) {
        System.out.println("updateAuthor called...");

        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isPresent()) {
            Author dbAuthor = optional.get();
            authorMapper.updateAuthorFromDto(dto, dbAuthor);
            Author updated = authorRepository.save(dbAuthor);
            return GenericResponse.success(authorMapper.authorToDto(updated));
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> deleteAuthor(Integer id) {
        System.out.println("deleteAuthor called...");

        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isPresent()) {
            authorRepository.delete(optional.get());
            return GenericResponse.success("Yazar başarıyla silindi.");
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> getBooksByAuthorId(Integer authorId) {
        System.out.println("getBooksByAuthorId called...");

        if (!authorRepository.existsById(authorId)) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }

        List<Book> books = bookRepository.findByAuthorId(authorId);
        if (books.isEmpty()) {
            return GenericResponse.error("Bu yazara ait kitap bulunamadı.");
        }

        List<DtoBook> dtoList = books.stream()
                .filter(book -> book.getAuthor() != null && book.getPublisher() != null && book.getCategory() != null)
                .map(bookMapperView::bookToDto)
                .collect(Collectors.toList());

        return GenericResponse.success(dtoList);
    }
}
