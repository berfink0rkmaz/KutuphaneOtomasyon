package org.example.kutuphaneotomasyon.Mapper;

import org.example.kutuphaneotomasyon.Dto.DtoAuthor;
import org.example.kutuphaneotomasyon.Dto.DtoAuthorIU;
import org.example.kutuphaneotomasyon.Entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author dtoToAuthor(DtoAuthorIU dto) {
        if (dto == null) return null;

        Author author = new Author();
        author.setAd(dto.getAd());
        author.setSoyad(dto.getSoyad());
        return author;
    }

    public DtoAuthor authorToDto(Author author) {
        if (author == null) return null;

        DtoAuthor dto = new DtoAuthor();
        dto.setId(author.getId());
        dto.setAd(author.getAd());
        dto.setSoyad(author.getSoyad());
        return dto;
    }

    public void updateAuthorFromDto(DtoAuthorIU dto, Author author) {
        if (dto == null || author == null) return;

        if (dto.getAd() != null) {
            author.setAd(dto.getAd());
        }
        if (dto.getSoyad() != null) {
            author.setSoyad(dto.getSoyad());
        }
    }
}