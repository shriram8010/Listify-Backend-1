package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.Service.ProductService;
import com.app.dto.ProductDto;
import com.app.entity.Product;




@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
 @GetMapping("/getallproduct")
 public List<ProductDto> getAllProduct(){
	 return productService.getAllproduct();
 }
 
 @GetMapping("/getproduct/{id}")
 public ProductDto getproduct(@PathVariable Long id) {
	 
	return productService.getProductById(id);
	 
 }
 
 @GetMapping("/getbylocation/{location}")
 public List<ProductDto> getProductByLocation(@PathVariable String location){
	 return productService.getProductByLocation(location);
 }
 
 @GetMapping("/getsearchproduct/{title}")
 public List<ProductDto> searchProduct(@PathVariable String title){
	 return productService.searchProduct(title);
 }
 
 @GetMapping("/getproductbyprice/{price}")
 public List<ProductDto> getProductByPrice(@PathVariable Double price){
	 
	 return productService.getProductByPrice(price);
 }
 
 @GetMapping("/getproductbytitle/{charactertofind}")
 public List<ProductDto> getProductByTitle(@PathVariable String charactertofind){
	 return productService.findProductsByNamePattern(charactertofind);
 }
}
