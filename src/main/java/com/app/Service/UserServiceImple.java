package com.app.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.Dao.UserDao;
import com.app.customexception.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.UserDto;
import com.app.entity.User;

@Service
@Transactional
public class UserServiceImple implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	@Override
	public ApiResponse registerUser(UserDto userDto) {
		String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
		userDto.setPassword(encryptedPassword);
		 User user =modelMapper.map(userDto, User.class);
		userDao.save(user);
		return new ApiResponse("User is added");
	}
	
	@Override
	public Optional<User> login(String email, String password) {
//		System.out.print("Encrypted pass: "+encryptedPassword);
		 Optional<User> user =userDao.findByEmailAndPassword(email, password);
		 System.out.println(user);
		return user;
	}
	
	public ApiResponse updateUser(Long userId, User updateUserRequest) {
        Optional<User> userOptional = userDao.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstName(updateUserRequest.getFirstName());
            user.setLastName(updateUserRequest.getLastName());
            user.setEmail(updateUserRequest.getEmail()); 
            user.setAddress(updateUserRequest.getAddress());
            user.setPhone(updateUserRequest.getPhone());
            userDao.save(user);
            return new ApiResponse("Book updated successfully");
        }
        else {
            throw new RuntimeException("Book not found with id " + userId);
        }
	}
	@Override
	public UserDto getUserById(Long Id) {
		User user=userDao.findById(Id).orElseThrow(()->new ResourceNotFoundException("Invalid user id"));
		UserDto userDto= modelMapper.map(user,UserDto.class);
		return userDto;
		
	}

}
