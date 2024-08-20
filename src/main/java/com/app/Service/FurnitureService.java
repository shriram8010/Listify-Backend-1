package com.app.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.FurnitureDto;

@Service
@Transactional
public interface FurnitureService {
	
	  public ApiResponse addFurniture(FurnitureDto furnitureDto);
	  
	  public List<FurnitureDto> getFurnitures();
	  
	  public ApiResponse updateFurniture(Long id, FurnitureDto furnitureDto);
	  
	  public FurnitureDto getFurnitureById(Long id);
	  
	  public ApiResponse deleteFurniture(Long id);
	  
	 
}
