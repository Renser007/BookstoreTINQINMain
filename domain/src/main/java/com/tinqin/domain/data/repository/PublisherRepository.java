package com.tinqin.domain.data.repository;

import com.tinqin.domain.data.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findPublisherByPublisherName(String publisherName);
}
