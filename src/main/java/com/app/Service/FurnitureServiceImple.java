package com.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.Dao.FurnitureDao;
import com.app.Dao.UserDao;
import com.app.customexception.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.ElectronicsDto;
import com.app.dto.FurnitureDto;
import com.app.entity.Book;
import com.app.entity.Electronics;
import com.app.entity.Furniture;
import com.app.entity.User;

@Service
@Transactional
public class FurnitureServiceImple implements FurnitureService{
	
	@Autowired
	private FurnitureDao furnitureDao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserDao userDao;
	

	@Override
	public List<FurnitureDto> getFurnitures() {
		// TODO Auto-generated method stub
		List<Furniture> furniture= furnitureDao.findAll();
		List<FurnitureDto> furnitureDto= convertToDto(furniture);
		
	       for(int i=0;i<furnitureDto.size();i++) {
	    	   furnitureDto.get(i).setUserId(furniture.get(i).getUser().getId());
	       }
	       
	       return furnitureDto;
	}
	
	public List<FurnitureDto> convertToDto(List<Furniture> furnitures) {
        return furnitures.stream()
                    .map(furniture -> modelMapper.map(furniture, FurnitureDto.class))
                    .collect(Collectors.toList());
    }

	@Override
	public ApiResponse addFurniture(FurnitureDto furnitureDto) {
		User user = userDao.findById(furnitureDto.getUserId())
				.orElseThrow(()->new ResourceNotFoundException("Invalid product id"));
		Furniture furniture = modelMapper.map(furnitureDto,Furniture.class);
		user.addUser(furniture);
		furnitureDao.save(furniture);
		return new ApiResponse("product added successfully");
	}
	
	   public ApiResponse updateFurniture(Long id, FurnitureDto furnitureDto) {
	        Optional<Furniture> optionalFurniture= furnitureDao.findById(id);
	        if (optionalFurniture.isPresent()) {
	            Furniture furniture = optionalFurniture.get();
	            modelMapper.map(furnitureDto, furniture);
	            furnitureDao.save(furniture);
	           return new ApiResponse("Furniture updated successfully");
	        } else {
	            throw new RuntimeException("Furniture not found with id " + id);
	        }
	    }

	@Override
	public FurnitureDto getFurnitureById(Long id) {
		Furniture ferniture=furnitureDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid product id"));
		FurnitureDto furnitureDto=modelMapper.map(ferniture, FurnitureDto.class);
		furnitureDto.setUserId(ferniture.getUser().getId());
		return furnitureDto;
	}

	 public ApiResponse deleteFurniture(Long id) {
		   Optional<Furniture> optionalFurniture = furnitureDao.findById(id);
	       if (optionalFurniture.isPresent()) {
	           Furniture furniture = optionalFurniture.get();
	           User user=userDao.findById(furniture.getUser().getId()).orElseThrow(()->new ResourceNotFoundException("Invalid id"));
	           user.removeUser(furniture);
	           furnitureDao.delete(furniture);
	          return new ApiResponse("Furniture deleted successfully");
	       } else {
	           throw new RuntimeException("Furniture not found with id " + id);
	       }
	   }

}
