package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
 public class Product extends Base{
       
       private String productTitle;
       
       private String productDescription;
       
       private String location;
       
       private double price;
       
       @ManyToOne
       private User user;
       
       private String imagePath;
       
       private String imagePath1;
       
       private String imagePath2;
       
       private String imagePath3;
       
       private String category;
}
