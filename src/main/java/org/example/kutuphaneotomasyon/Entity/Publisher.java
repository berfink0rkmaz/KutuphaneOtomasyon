package org.example.kutuphaneotomasyon.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="ad", nullable=false)
    private String ad;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> books;
}