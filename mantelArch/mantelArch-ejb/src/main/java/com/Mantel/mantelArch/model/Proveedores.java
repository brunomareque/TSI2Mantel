package com.Mantel.mantelArch.model;

public class Proveedores extends Usuario {

	private String Rut;
	private String NombreFantasia;
		
	public Proveedores(String username, String password, String email, 
						String direccion, String telefono, String rut, String nombreFantasia) {
		super(username, password, email, direccion, telefono);
		Rut = rut;
		NombreFantasia = nombreFantasia;
	}
	public String getRut() {
		return Rut;
	}
	public void setRut(String rut) {
		Rut = rut;
	}
	public String getNombreFantasia() {
		return NombreFantasia;
	}
	public void setNombreFantasia(String nombreFantasia) {
		NombreFantasia = nombreFantasia;
	}	
}
