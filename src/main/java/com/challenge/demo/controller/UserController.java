package com.challenge.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.challenge.demo.controller.dto.LoginResponse;
import com.challenge.demo.controller.dto.SignUpRequest;
import com.challenge.demo.controller.dto.SignUpResponse;
import com.challenge.demo.error.TokenExpiradoException;
import com.challenge.demo.jwt.JwtTokenUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.challenge.demo.entity.User;
import com.challenge.demo.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(tags = "Usuarios")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping( value = "/sign-up", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE } )
	public ResponseEntity<SignUpResponse>  SignUp(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {

		User user = userService.generarUsuario(signUpRequest.toEntity());
		final String token = jwtTokenUtil.generateToken(user);

		SignUpResponse response = new SignUpResponse(user.getUserId().toString(),user.getCreated(), null,token, user.getActive());
		return new ResponseEntity<SignUpResponse>(response,HttpStatus.OK);
	}

	@GetMapping(value =  "/login/{token}" , produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public ResponseEntity<LoginResponse>  login(@Valid @PathVariable String token) throws Exception {

		  String email = jwtTokenUtil.getUsernameFromToken(token);
		  Date date =	jwtTokenUtil.getExpirationDateFromToken(token);

		  if (LocalDateTime.now().isAfter(convertLodalDateTime(date)))
			   throw new TokenExpiradoException("el token ingresado esta expirado");

		  User user = userService.antenticate(email);

		  LoginResponse loginResponse = new LoginResponse();
		  loginResponse.setId(user.getUserId().toString());
		  loginResponse.setCreated(user.getCreated().toString());
		  loginResponse.setEmail(user.getEmail());
		  loginResponse.setPassword(user.getPassword());
		  loginResponse.setName(user.getName());
		  loginResponse.setToken(token);

		return new ResponseEntity<>(loginResponse,HttpStatus.OK);
	}


	public LocalDateTime convertLodalDateTime(Date dateToConvert) {
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
	}
}
