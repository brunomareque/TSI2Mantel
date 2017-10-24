package com.Mantel.mantelArch.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Usuario")
public class Usuario {

	@Id
	@GeneratedValue
	private int Id;
	private String Username;
	private String Password;
	private String Email;
	private String Direccion;
	private String Telefono;
	
	public Usuario(){
		
	}
	
	public Usuario(String username, String password, String email, String direccion, String telefono) {
		Username = username;
		Password = password;
		Email = email;
		Direccion = direccion;
		Telefono = telefono;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public int getId() {
		return Id;
	}
	
	
	
	
}
