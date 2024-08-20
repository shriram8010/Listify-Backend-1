package com.app.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.dto.ProductDto;
import com.app.entity.Product;

@Service
@Transactional
public interface ProductService {
   public List<ProductDto> getAllproduct();
   public ProductDto getProductById(Long id);
   public List<ProductDto> getProductByLocation(String location);
   
   public List<ProductDto> searchProduct(String title);
   
	public List<ProductDto> getProductByPrice(Double maxPrice);
	
	public List<ProductDto> findProductsByNamePattern(String character);
}
