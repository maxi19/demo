package com.challenge.demo.service.impl;

import java.time.LocalDate;

import com.challenge.demo.error.ExisteUsuarioException;
import com.challenge.demo.error.TokenExpiradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.demo.entity.User;
import com.challenge.demo.repository.UserRepository;
import com.challenge.demo.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository; 

	@Override
	public User generarUsuario(User user) throws Exception {

		User userByEmail  = userRepository.findByEmail(user.getEmail());
		if (userByEmail != null &&  userByEmail.getActive() == false )
			throw new ExisteUsuarioException("El email ingresado ya esta registrado");

		if (userByEmail != null){
			userByEmail.setActive(new Boolean(false));
			return userRepository.save(userByEmail);
		}else{
			user.setCreated(LocalDate.now());
			return userRepository.save(user);
		}

	}

	@Override
	public User antenticate(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new ExisteUsuarioException("El usuario no existe");

		if (user != null && user.getActive() == true)
			throw new TokenExpiradoException("el token ingresado ya fue utilizado");

		user.setActive(true);
		userRepository.save(user);
		return user;
	}


}
