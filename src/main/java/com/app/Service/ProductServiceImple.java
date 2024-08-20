package com.app.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Dao.ProductDao;
import com.app.customexception.ResourceNotFoundException;
import com.app.dto.BookDto;
import com.app.dto.ProductDto;
import com.app.entity.Book;
import com.app.entity.Product;

@Service
@Transactional
public class ProductServiceImple implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<ProductDto> getAllproduct() {
		
		return convertToDto(productDao.findAll());
	}
	
	public List<ProductDto> convertToDto(List<Product> products) {
        return products.stream()
                    .map(product -> modelMapper.map(product, ProductDto.class))
                    .collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(Long id) {
		// TODO Auto-generated method stub
		Product product=productDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid product id"));
		ProductDto productDto=modelMapper.map(product,ProductDto.class);
		return productDto;
	}
	
	public List<ProductDto> getProductByLocation(String location){
		List<Product> products=productDao.findByLocation(location);
	
		return convertToDto(products);
	}
	
	public List<ProductDto> searchProduct(String title){
		List<Product> products=productDao.searchProduct(title);
		
		return convertToDto(products);
	}
	
	public List<ProductDto> getProductByPrice(Double maxPrice){
		List<Product> products=productDao.getProductByPrice(maxPrice);
		for (Product product : products) {
			System.out.println("product");
		}
		return convertToDto(products);
		
	}
	
	public List<ProductDto> findProductsByNamePattern(String character) {
	    String jpql = "SELECT p FROM Product p WHERE p.productTitle LIKE :productTitle";
	    return convertToDto(entityManager.createQuery(jpql, Product.class)
	             .setParameter("productTitle", "%" + character + "%")
	             .getResultList());
	}
}
