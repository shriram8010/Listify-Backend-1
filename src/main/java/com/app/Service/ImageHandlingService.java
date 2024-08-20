package com.app.Service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.ElectronicsDto;
import com.app.dto.FurnitureDto;
import com.app.dto.VehicleDto;
import com.app.entity.Book;
import com.app.entity.Product;

public interface ImageHandlingService {
	ApiResponse uploadImage(Long empId, MultipartFile[] images) throws IOException;
	byte[] serveImage(Long empId) throws IOException;
	//used for uploading img along with emp details
	String uploadImage(BookDto bookDto, MultipartFile[] images) throws IOException;
	String uploadImage(ElectronicsDto electronicsDto, MultipartFile[] images) throws IOException;
	String uploadImage(FurnitureDto furnitureDto, MultipartFile[] images) throws IOException;
	String uploadImage(VehicleDto vehicleDto, MultipartFile[] images) throws IOException;
}

