package com.tinqin.domain.data.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genre")
@Getter
@Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    private String genreName;

    @OneToMany(mappedBy = "genre")
    private Set<Book> books=new HashSet<>();

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(genreId, genre.genreId) && Objects.equals(genreName, genre.genreName) && Objects.equals(books, genre.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, genreName, books);
    }
}
