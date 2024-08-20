package com.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Dao.ElectronicsDao;
import com.app.Dao.UserDao;
import com.app.customexception.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.ElectronicsDto;
import com.app.entity.Book;
import com.app.entity.Electronics;
import com.app.entity.User;

@Service
@Transactional
public class ElectronicsServiceImple implements ElectronicsService{
	@Autowired
	private ElectronicsDao electronicsDao;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ApiResponse addElectronics(ElectronicsDto electronicsDto) {
		User user = userDao.findById(electronicsDto.getUserId())
				.orElseThrow(()->new ResourceNotFoundException("Invalid product id"));
		Electronics electronics = modelMapper.map(electronicsDto,Electronics.class);
		user.addUser(electronics);
		electronicsDao.save(electronics);
		return new ApiResponse("product added successfully");
	}

	@Override
	public List<ElectronicsDto> getElectronics() {	
		List<Electronics> electronics= electronicsDao.findAll();
		List<ElectronicsDto> electronicsDto= convertToDto(electronics);
		
	       for(int i=0;i<electronicsDto.size();i++) {
	    	   electronicsDto.get(i).setUserId(electronics.get(i).getUser().getId());
	       }
	       
	       return electronicsDto;
	}
	
	public List<ElectronicsDto> convertToDto(List<Electronics> electronics) {
        return electronics.stream()
                    .map(item -> modelMapper.map(item, ElectronicsDto.class))
                    .collect(Collectors.toList());
    }
	
    public ApiResponse updateElectronics(Long id, ElectronicsDto electronicsDto) {
        Optional<Electronics> optionalElectronics = electronicsDao.findById(id);
        if (optionalElectronics.isPresent()) {
        	Electronics electronics = optionalElectronics.get();
            modelMapper.map(electronicsDto, electronics);
            electronicsDao.save(electronics);
           return new ApiResponse("Electronics updated successfully");
        } else {
            throw new RuntimeException("Electronics not found with id " + id);
        }
    }
    
   public ElectronicsDto getElectronicsById(Long Id) {	
    Electronics electronics=electronicsDao.findById(Id).orElseThrow(()->new ResourceNotFoundException("Invalid product id")); 	
     ElectronicsDto electronicsDto= modelMapper.map(electronics,ElectronicsDto.class);
     electronicsDto.setUserId(electronics.getUser().getId());
    return electronicsDto; 	
    }

   public ApiResponse deleteElectronics(Long id) {
	   Optional<Electronics> optionalElectronics = electronicsDao.findById(id);
       if (optionalElectronics.isPresent()) {
          Electronics electronics= optionalElectronics.get();
           User user=userDao.findById(electronics.getUser().getId()).orElseThrow(()->new ResourceNotFoundException("Invalid id"));
           user.removeUser(electronics);
          electronicsDao.delete(electronics);
          return new ApiResponse("Electronics deleted successfully");
       } else {
           throw new RuntimeException("Electronics not found with id " + id);
       }
   }
}
