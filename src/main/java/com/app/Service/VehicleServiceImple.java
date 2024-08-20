package com.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Dao.UserDao;
import com.app.Dao.VehicleDao;
import com.app.customexception.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.VehicleDto;
import com.app.entity.Book;
import com.app.entity.User;
import com.app.entity.Vehicle;

@Service
@Transactional
public class VehicleServiceImple implements VehicleService {

	
	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ApiResponse addVehicle(VehicleDto vehicleDto) {
      User user=userDao.findById(vehicleDto.getUserId()).orElseThrow(()->new ResourceNotFoundException("Invalid product id"));
      
      Vehicle vehicle=modelMapper.map(vehicleDto,Vehicle.class);
      
      user.addUser(vehicle);
      
      vehicleDao.save(vehicle);
        
		return  new ApiResponse("Vehicle added succesfully");
	}

	@Override
	public List<VehicleDto> getAllVehicle() {
		List<Vehicle> vehicle= vehicleDao.findAll();
		List<VehicleDto> vehicleDto= convertToDto(vehicle);
		
	       for(int i=0;i<vehicleDto.size();i++) {
	    	   vehicleDto.get(i).setUserId(vehicle.get(i).getUser().getId());
	       }
	       
	       return vehicleDto;
	}
	
	public List<VehicleDto> convertToDto(List<Vehicle> vehicles) {
        return vehicles.stream()
                    .map(vehicle -> modelMapper.map(vehicle, VehicleDto.class))
                    .collect(Collectors.toList());
    }
	
	 public ApiResponse updateVehicle(Long id, VehicleDto vehicleDto) {
	        Optional<Vehicle> optionalVehicle = vehicleDao.findById(id);
	        if (optionalVehicle.isPresent()) {
	           Vehicle vehicle = optionalVehicle.get();
	            modelMapper.map(vehicleDto, vehicle);
	            vehicleDao.save(vehicle);
	           return new ApiResponse("Vehicle updated successfully");
	        } else {
	            throw new RuntimeException("Vehicle not found with id " + id);
	        }
	    }
     
	 
	 public VehicleDto getVehicleById(Long Id) {	
		    Vehicle vehicle=vehicleDao.findById(Id).orElseThrow(()->new ResourceNotFoundException("Invalid product id")); 	
		    VehicleDto vehicleDto= modelMapper.map(vehicle,VehicleDto.class);
		    vehicleDto.setUserId(vehicle.getUser().getId());
		    return vehicleDto; 	
		    }
	 
	 public ApiResponse deleteVehicle(Long id) {
		   Optional<Vehicle> optionalVehicle = vehicleDao.findById(id);
	       if (optionalVehicle.isPresent()) {
	           Vehicle vehicle = optionalVehicle.get();
	           User user=userDao.findById(vehicle.getUser().getId()).orElseThrow(()->new ResourceNotFoundException("Invalid id"));
	           user.removeUser(vehicle);
	           vehicleDao.delete(vehicle);
	          return new ApiResponse("Vehicle deleted successfully");
	       } else {
	           throw new RuntimeException("Vehicle not found with id " + id);
	       }
	   }
	
}
