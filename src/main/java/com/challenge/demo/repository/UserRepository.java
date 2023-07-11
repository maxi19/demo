package com.challenge.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenge.demo.entity.User;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    User findByEmail(String nombre);

    User findByNameAndEmail(String nombre, String email);

    User findByEmailAndPassword(String email, String passsword);
}
