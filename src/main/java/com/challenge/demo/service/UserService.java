package com.challenge.demo.service;


import com.challenge.demo.entity.User;

public interface UserService {

	public User generarUsuario(User user) throws Exception;

	public User antenticate(String email) throws Exception;

}
