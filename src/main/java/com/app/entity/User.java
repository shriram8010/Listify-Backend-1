package com.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

//import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Base {
	
	private String firstName; //VARCHAR(25)
	private String lastName ;//VARCHAR(25)
	private String email ;//VARCHAR(50)
	private String password ;//VARCHAR(200)
	private String address ;//VARCHAR(200)
	private String phone ;//VARCHAR(15)

	
	
	@OneToMany(mappedBy = "user")
	private List<Product> productList=new ArrayList<Product>();
	
    public void addUser(Product product) {
   		this.productList.add(product);
  		product.setUser(this);
  	}
    
	public void removeUser(Product product) {
		this.productList.remove(product);
		product.setUser(null);
	}
	public void addUser(Furniture furniture) {
   		this.productList.add(furniture);
   		furniture.setUser(this);
  	}
    
	public void removeUser(Furniture furniture) {
		this.productList.remove(furniture);
		furniture.setUser(null);
	}
	public void addUser(Vehicle vehicle) {
   		this.productList.add(vehicle);
   		vehicle.setUser(this);
  	}
    
	public void removeUser(Vehicle vehicle) {
		this.productList.remove(vehicle);
		vehicle.setUser(null);
	}
	public void addUser(Electronics electronics) {
   		this.productList.add(electronics);
   		electronics.setUser(this);
  	}
    
	public void removeUser(Electronics electronics) {
		this.productList.remove(electronics);
		electronics.setUser(null);
	}
	
	public String getRole() {
		return "User";
	}

	
	
}
