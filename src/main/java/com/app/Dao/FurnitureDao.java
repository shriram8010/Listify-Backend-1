package com.app.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Book;
import com.app.entity.Furniture;



@Repository
public interface FurnitureDao extends JpaRepository<Furniture, Long> {
	public Optional<Furniture> findById(Long Id);
}
