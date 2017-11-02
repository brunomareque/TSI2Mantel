package com.Mantel.mantelArch.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Peliculas") 
@Entity
@Table(name="\"Peliculas\"")
@NamedQuery(name="Peliculas.findAll", query="SELECT p FROM Peliculas p")
public class Peliculas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @XmlElement 
	@Id
	private String Nombre;
	 
	 
	public Peliculas() {
		
	}
	@XmlElement 
	public String getNombre() {
		return this.Nombre;
	}
	@XmlElement 
	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}
	
	
	
}
