package com.app.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.BookDto;
import com.app.dto.ElectronicsDto;

@Service
@Transactional
public interface ElectronicsService {
	public ApiResponse addElectronics(ElectronicsDto electronicsDto);
	  public List<ElectronicsDto> getElectronics();
	  public ApiResponse updateElectronics(Long id, ElectronicsDto electronicsDto);
	  public ElectronicsDto getElectronicsById(Long Id);
	  public ApiResponse deleteElectronics(Long id);
}
