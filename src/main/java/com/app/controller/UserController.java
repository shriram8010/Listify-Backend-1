package com.app.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.app.Service.UserService;
import com.app.dto.ApiResponse;
import com.app.dto.ErrorDto;
import com.app.dto.LoginDTO;
import com.app.dto.SigninResponse;
import com.app.dto.UserDto;
import com.app.entity.User;
import com.app.security.JwtUtils;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authMgr;
	
	@PostMapping("/register")
	public ResponseEntity<?> addNewUser(@RequestBody UserDto userdto){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userdto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user is not added");
		}
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
    try {
		System.out.println("in sign in" + loginDTO);
		//create a token(implementation of Authentication i/f)
		//to store un verified user email n pwd
		UsernamePasswordAuthenticationToken token=new 
				UsernamePasswordAuthenticationToken(loginDTO.getEmail(), 
						loginDTO.getPassword());
		//invoke auth mgr's authenticate method;
		Authentication verifiedToken = authMgr.authenticate(token);
		//=> authentication n authorization  successful !
		System.out.println(verifiedToken.getPrincipal().getClass());//custom user details object
		//create JWT n send it to the clnt in response
		SigninResponse resp=new SigninResponse
				(jwtUtils.generateJwtToken(verifiedToken),
				"Successful Auth!!!!");
		return ResponseEntity.
				status(HttpStatus.CREATED).body(resp);
    }catch(Exception e) {
    	ErrorDto errorDto=new ErrorDto("error");
    	return ResponseEntity.ok().body(errorDto);
    }
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody UserDto updateUserRequestDto) {
		User updateUserRequest=modelMapper.map(updateUserRequestDto,User.class);

        
            return ResponseEntity.ok(userService.updateUser(id, updateUserRequest));
        
    }
	
	@GetMapping("/get/{id}")
	 public ResponseEntity<?> getBook(@PathVariable Long id) {
		 try {
			       
				return ResponseEntity.ok(userService.getUserById(id));
			} catch (RuntimeException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	 }
}
