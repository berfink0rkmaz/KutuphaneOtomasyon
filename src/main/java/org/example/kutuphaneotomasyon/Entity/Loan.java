package org.example.kutuphaneotomasyon.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Where(clause="deleted=false")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    public LocalDate borrowDate;
    public LocalDate dueDate;
    public LocalDate returnDate;
    private boolean deleted=false;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "borrow_book",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "borrow_id", referencedColumnName = "id"))
    private Book borrowedBook;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "borrow_user",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "borrow_id", referencedColumnName = "id"))
    private List<User> UserList;

}
