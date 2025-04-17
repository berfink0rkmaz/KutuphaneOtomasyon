package org.example.kutuphaneotomasyon.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Getter
@Setter
@Where(clause="deleted=false")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
    public enum Role {
        ADMIN,LIBRARIAN,STUDENT
    }

    private boolean deleted=false;

    @OneToMany(mappedBy = "user")
    private List<Loan> loans;

}
