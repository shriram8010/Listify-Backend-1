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
public class VehicleDto {
	private Long id;
	@NotEmpty
    private String productTitle;
    @NotEmpty
    private String productDescription;
    
    private double price;
    
    private String location;
    
    @NotNull
    private Long userId;
    
   private String manufaturer;
	
	private String vehicleType;
	
	private String fuelType;
	
	private String modelName;
	
	private LocalDate manufaturerDate;
	
	private LocalDate purchaseDate;
	
    private String imagePath;
     
     private String imagePath1;
     
     private String imagePath2;
     
     private String imagePath3;
     
     private LocalDate createdOn;
     
     private String category;
}
