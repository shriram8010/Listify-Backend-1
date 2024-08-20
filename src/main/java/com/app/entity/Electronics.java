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
public class Electronics extends Product {
	private String manufaturer;
	   
    private LocalDate yearOfPurchase;
    
    private String type;
    
    private String modelName;
    
 
}
