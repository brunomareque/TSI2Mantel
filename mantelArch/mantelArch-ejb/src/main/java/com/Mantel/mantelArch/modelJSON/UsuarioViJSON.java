package com.Mantel.mantelArch.modelJSON;

import org.bson.types.ObjectId;

public class UsuarioViJSON {
	
	public ObjectId id;
	public String Titulo;
	public String Username;
	public int UltimoSeg;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public int getUltimoSeg() {
		return UltimoSeg;
	}
	public void setUltimoSeg(int ultimoSeg) {
		UltimoSeg = ultimoSeg;
	}
	
	public UsuarioViJSON( String titulo, String username, int ultimoSeg) {
		
		Titulo = titulo;
		Username = username;
		UltimoSeg = ultimoSeg;
	}
	
	public UsuarioViJSON() {

	}
	
	

}
