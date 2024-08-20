package com.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
	List<Product> findByLocation(String location);

	@Query("SELECT p FROM Product p WHERE p.productTitle LIKE :title")
	List<Product> searchProduct(String title);


	
	@Query("SELECT p FROM Product p WHERE p.price < :maxPrice")
	List<Product> getProductByPrice(Double maxPrice);
	
}
