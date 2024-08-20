package com.app.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.app.Service.BookService;
import com.app.Service.ImageHandlingService;
import com.app.Service.UserService;
import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.entity.Book;

import io.jsonwebtoken.io.IOException;
import io.swagger.v3.core.model.ApiDescription;

@RestController
@CrossOrigin
public class BookController {
  
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ImageHandlingService imageHandlingService;
	
	@Autowired
	private ObjectMapper objectmapper;
	
//	@PostMapping("/book/addproduct")
//	public ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {
//		
//		try {
//			return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookDto));
//		} catch (RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
//		}
//	}
	
	@GetMapping("/book/getallProduct")
	public List<BookDto> getBooks(){
        return bookService.getBooks();	
	}

	
	 @PutMapping("/book/{id}")
	    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
			try {
			return ResponseEntity.ok(bookService.updateBook(id,bookDto));
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	        
	    }
	 
	 @GetMapping("/book/get/{id}")
	 public ResponseEntity<?> getBook(@PathVariable Long id) {
		 try {
				return ResponseEntity.ok(bookService.getBookById(id));
			} catch (RuntimeException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	 }
	 
	 @DeleteMapping("/book/delete/{id}")
	 public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		 try {
				return ResponseEntity.ok(bookService.deleteBook(id));
			} catch (RuntimeException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	 }
	 
		@PostMapping(value = "/book/images", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
		public ResponseEntity<?> uploadEmpAndImage(@RequestParam("image") MultipartFile[]
				images, @RequestParam("bookdata") String value)
			throws IOException {
			try {
			System.out.println(value);
			BookDto bookDto=objectmapper.readValue(value,BookDto.class);
			Book book=modelMapper.map(bookDto,Book.class);
			System.out.println("in upload emp details n image " + book + " " + images);
			System.out.println(bookDto.toString());
//			
		
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(imageHandlingService.uploadImage(bookDto, images));
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.
						status(HttpStatus.NOT_FOUND)
						.body("problem to upload image");
			}
		}
		
		
		@PostMapping(value = "/book/images/{productId}",
				consumes = "multipart/form-data")
		public ResponseEntity<?> uploadImage(@PathVariable 
				Long productId, 
				@RequestParam MultipartFile[] images)
				throws IOException {
			System.out.println("in upload image " + productId);
			try {
				return ResponseEntity.status(HttpStatus.CREATED).
						body(imageHandlingService.uploadImage(productId, images));
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				return ResponseEntity.
						status(HttpStatus.NOT_FOUND)
						.body("problem to upload image");
			}
		}
		
		@GetMapping(value = "/book/images/{productId}")
//				produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE }
		
		public ResponseEntity<?> downloadImage(@PathVariable Long productId) throws IOException {
			System.out.println("in download image " + productId);
			try {
				return ResponseEntity.ok(imageHandlingService.serveImage(productId));
			} catch (java.io.IOException e) {
				return ResponseEntity.
						status(HttpStatus.NOT_FOUND)
						.body("image not found");
			}
		}
	 
	 
	
}
