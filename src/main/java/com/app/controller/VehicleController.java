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
import com.app.Service.ImageHandlingService;
import com.app.Service.VehicleService;
import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.VehicleDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;

@RestController
@CrossOrigin
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ObjectMapper objectmapper;
	
	@Autowired
	private ImageHandlingService imageHandlingService;
	
	@PostMapping("/Vehicle/addproduct")
	public ResponseEntity<?> addBook(@RequestBody VehicleDto vehicleDto) {
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.addVehicle(vehicleDto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	 @GetMapping("/vehicle/getallProduct")
	 public List<VehicleDto> getVehicles(){
        return vehicleService.getAllVehicle();	
	 }
	 
	 @PutMapping("/vehicle/{id}")
	    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
			try {
			return ResponseEntity.ok(vehicleService.updateVehicle(id,vehicleDto));
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	        
	    }
	 
	 @GetMapping("/vehicle/get/{id}")
	 public ResponseEntity<?> getVehicle(@PathVariable Long id) {
		 try {
				return ResponseEntity.ok(vehicleService.getVehicleById(id));
			} catch (RuntimeException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	 }
	 
	 @DeleteMapping("/vehicle/delete/{id}")
	 public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
		 try {
				return ResponseEntity.ok(vehicleService.deleteVehicle(id));
			} catch (RuntimeException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	 }
	 
	 @PostMapping(value = "/vehicle/images", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
		public ResponseEntity<?> uploadEmpAndImage(@RequestParam("image") MultipartFile[]
				images, @RequestParam("vehicledata") String value)
			throws IOException {
			try {
			System.out.println(value);
			VehicleDto vehicleDto=objectmapper.readValue(value,VehicleDto.class);

				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(imageHandlingService.uploadImage(vehicleDto, images));
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.
						status(HttpStatus.NOT_FOUND)
						.body("problem to upload image");
			}
		}
	
	
}
