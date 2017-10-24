package com.Mantel.mantelArch.model;

public class Clientes extends Usuario {

	private String Nombre;
	private String Apellido;
	private String NumeroTarjeta;
	
	
	public Clientes(String nombre, String apellido, String numeroTarjeta) {
		super();
		Nombre = nombre;
		Apellido = apellido;
		NumeroTarjeta = numeroTarjeta;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getNumeroTarjeta() {
		return NumeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		NumeroTarjeta = numeroTarjeta;
	}
	
	
}
