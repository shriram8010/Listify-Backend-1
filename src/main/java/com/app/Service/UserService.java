package com.app.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.UserDto;
import com.app.entity.User;

@Service
@Transactional
public interface UserService {
	public ApiResponse registerUser(UserDto userDto);
	public Optional<User> login(String email, String password);
	public ApiResponse updateUser(Long userId, User updateUserRequest);
	public UserDto getUserById(Long Id);
}
