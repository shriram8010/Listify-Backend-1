package com.app.entity;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@MappedSuperclass
@Getter
@Setter
@ToString
public class Base {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private LocalDate createdOn;
	
	@UpdateTimestamp 
	private LocalDate updatedOn;
}
