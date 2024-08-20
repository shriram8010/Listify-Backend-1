package com.app.dto;

import java.time.LocalDate;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.app.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDto {
	
	private Long id;
	
	@NotEmpty
	 private String productTitle;
	
     private String productDescription;
     @NotEmpty
     private double price;
     
     private String location;
     
     private String author;
     
     @NotNull
     private Long userId;
     
     private String conditionOfBook;
     
     private String yearOfPublication;
     
     @JsonProperty(access = Access.READ_ONLY)
     private String imagePath;
     @JsonProperty(access = Access.READ_ONLY)
     private String imagePath1;
     @JsonProperty(access = Access.READ_ONLY)
     private String imagePath2;
     @JsonProperty(access = Access.READ_ONLY)
     private String imagePath3;
     
     private LocalDate createdOn;
     
     private String category;
     
     
     
}
