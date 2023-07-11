package com.challenge.demo.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name="user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID userId;

	@Column(name="name")
	private String name;

	@Column(name="email")
	private String email;

	@Column(name="password")
	private String password;

	@Column(name="created")
	private LocalDate created;

	@Column(name="lastLogin")
	private LocalDate lastLogin;

	@Column(name="active")
	private Boolean active;

	public User() {
		super();
	}

	public User(String name, String email, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(String name, String email, String password, Boolean active) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.active = active;
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
