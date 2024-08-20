package com.app.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {
    public Optional<Book> findById(Long Id);
}
