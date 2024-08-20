package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicsDto {
	
	private Long id;
	
	@NotEmpty
     private String productTitle;
    
    private String productDescription;
    
    private String location;
    
    
    @NotEmpty
    private double price;
    
    @NotNull
    private Long userId;
    
    private String manufaturer;
	   
    private LocalDate yearOfPurchase;
    
    private String modelName;
    
    private String imagePath;
    
    private String imagePath1;
    
    private String imagePath2;
    
    private String imagePath3;
    
    private LocalDate createdOn;
    
    private String category;
    private String type;
}

