package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.Service.BookService;
import com.app.Service.FurnitureService;
import com.app.Service.ImageHandlingService;
import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.ElectronicsDto;
import com.app.dto.FurnitureDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;

@RestController
@CrossOrigin
public class FurnitureController {
	@Autowired
	private FurnitureService furnitureService;
	
	@Autowired
	private ObjectMapper objectmapper;
	
	@Autowired
	private ImageHandlingService imageHandlingService;
	
	
	
	@GetMapping("/furniture/getallProduct")
	public List<FurnitureDto> getFurnitures(){
    return furnitureService.getFurnitures();	
	}
	
	@PostMapping("/furniture/addproduct")
	public ResponseEntity<?> addBook(@RequestBody FurnitureDto furnitureDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(furnitureService.addFurniture(furnitureDto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	 @PutMapping("/furniture/{id}")
	    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody FurnitureDto furnitureDto) {
			try {
			return ResponseEntity.ok(furnitureService.updateFurniture(id,furnitureDto));
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	        
	    }
	 
	 @GetMapping("/furniture/get/{id}")
	 public ResponseEntity<?>  getBook(@PathVariable Long id) {
		 try {
				return ResponseEntity.ok(furnitureService.getFurnitureById(id));
			} catch (RuntimeException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	 }
	
	 @DeleteMapping("/furniture/delete/{id}")
	 public ResponseEntity<?> deletefurniture(@PathVariable Long id) {
		 try {
				return ResponseEntity.ok(furnitureService.deleteFurniture(id));
			} catch (RuntimeException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	 }
	 
	 @PostMapping(value = "/furniture/images", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
		public ResponseEntity<?> uploadEmpAndImage(@RequestParam("image") MultipartFile[]
				images, @RequestParam("furnituredata") String value)
			throws IOException {
			try {
			System.out.println(value);
			FurnitureDto furnitureDto=objectmapper.readValue(value,FurnitureDto.class);
		
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(imageHandlingService.uploadImage(furnitureDto, images));
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.
						status(HttpStatus.NOT_FOUND)
						.body("problem to upload image");
			}
		}
	
	 
}
