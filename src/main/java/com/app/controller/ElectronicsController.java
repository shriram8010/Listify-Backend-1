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

import com.app.Service.ElectronicsService;
import com.app.Service.ImageHandlingService;
import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.ElectronicsDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;


@RestController
@CrossOrigin
public class ElectronicsController {

	@Autowired
	private ElectronicsService electronicsService;
	
	@Autowired
	private ImageHandlingService imageHandlingService;
	
	@Autowired
	private ObjectMapper objectmapper;
	
	@PostMapping("/electronics/addproduct")
	public ResponseEntity<?> addElectronics(@RequestBody ElectronicsDto electronicsDto) {
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(electronicsService.addElectronics(electronicsDto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/electronics/getallProduct")
	public List<ElectronicsDto> getElectronics(){
        return electronicsService.getElectronics();	
	}

	
	 @PutMapping("/electronics/{id}")
	    public ResponseEntity<?> updateElectronics(@PathVariable Long id, @RequestBody ElectronicsDto electronicsDto) {
			try {
			return ResponseEntity.ok(electronicsService.updateElectronics(id,electronicsDto));
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	        
	    }
	 
	 @GetMapping("/electronics/get/{id}")
	 public ResponseEntity<?> getElectronics(@PathVariable Long id) {
		 try {
				return ResponseEntity.ok(electronicsService.getElectronicsById(id));
			} catch (RuntimeException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	 }
	 
	 @DeleteMapping("/electronics/delete/{id}")
	 public ResponseEntity<?> deleteElectronics(@PathVariable Long id) {
		 try {
				return ResponseEntity.ok(electronicsService.deleteElectronics(id));
			} catch (RuntimeException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	 }
	 
	 @PostMapping(value = "/electronics/images", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
		public ResponseEntity<?> uploadEmpAndImage(@RequestParam("image") MultipartFile[]
				images, @RequestParam("electronicsdata") String value)
			throws IOException {
			try {
			System.out.println(value);
			ElectronicsDto electronicsDto=objectmapper.readValue(value,ElectronicsDto.class);

				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(imageHandlingService.uploadImage(electronicsDto, images));
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.
						status(HttpStatus.NOT_FOUND)
						.body("problem to upload image");
			}
		}
	}
