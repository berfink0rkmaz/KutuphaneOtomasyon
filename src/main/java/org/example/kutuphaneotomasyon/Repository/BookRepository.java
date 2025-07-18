package org.example.kutuphaneotomasyon.Repository;

import org.example.kutuphaneotomasyon.Entity.Book;
import org.example.kutuphaneotomasyon.Entity.Durum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookById(int id);
    List<Book> findByAuthorId(Integer authorId);
    List<Book> findByPublisherId(Integer publisherId);
    List<Book> findByCategoryId(Integer categoryId);
    @Query(value = "SELECT * FROM book WHERE ad ILIKE %:keyword%", nativeQuery = true)
    List<Book> searchByName(@Param("keyword") String keyword);
    long countByDurum(Durum durum);
    @Query(value = "SELECT * FROM books WHERE id = ':id'", nativeQuery = true)
    Book findBookWithRawSql(@Param("id") String id);




}