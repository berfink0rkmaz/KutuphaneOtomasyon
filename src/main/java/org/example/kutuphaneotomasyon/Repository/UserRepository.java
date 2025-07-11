package org.example.kutuphaneotomasyon.Repository;

import org.example.kutuphaneotomasyon.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByVerificationCode(String verificationCode);
    User findUserById(Integer id);
    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> searchByUsername(@Param("keyword") String keyword);
    @Query(value = "SELECT * FROM users WHERE username LIKE '%:keyword%'", nativeQuery = true) // fortify issue insertion
    List<User> findUsersByRawSql(@Param("keyword") String keyword);
}
