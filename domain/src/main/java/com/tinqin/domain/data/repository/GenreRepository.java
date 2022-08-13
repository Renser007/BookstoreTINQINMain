package com.tinqin.domain.data.repository;

import com.tinqin.domain.data.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> getGenreByGenreName(String genre);
}
