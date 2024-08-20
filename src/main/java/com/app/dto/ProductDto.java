package com.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	private Long id;
	
	private String productTitle;
    
    private String productDescription;
    
    private double price;
    
    private String imagePath;
    
    private String location;
    
    private String category;
    
    private LocalDate createdOn;
}
