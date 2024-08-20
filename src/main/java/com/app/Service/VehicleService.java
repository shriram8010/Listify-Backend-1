package com.app.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.VehicleDto;

@Service
@Transactional
public interface VehicleService {
	public ApiResponse addVehicle(VehicleDto vehicleDto);
	public List<VehicleDto> getAllVehicle();
	 public ApiResponse updateVehicle(Long id, VehicleDto vehicleDto);
	 public VehicleDto getVehicleById(Long Id);
	 public ApiResponse deleteVehicle(Long id);
}
