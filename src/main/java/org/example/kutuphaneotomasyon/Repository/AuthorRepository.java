package org.example.kutuphaneotomasyon.Repository;


import org.example.kutuphaneotomasyon.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findAuthorById(int id);


    Optional<Author> findByAdAndSoyad(String authorAd, String authorSoyad);
}
