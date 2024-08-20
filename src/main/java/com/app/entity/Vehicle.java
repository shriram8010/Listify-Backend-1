package com.app.entity;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends Product{
	private String manufaturer;
	
	private String vehicleType;
	
	private String fuelType;
	
	private String modelName;
	
	private LocalDate manufaturerDate;
	
	private LocalDate purchaseDate;
	
  

}
