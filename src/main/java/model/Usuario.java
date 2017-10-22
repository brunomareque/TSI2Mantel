package model;


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
	private String Nombre;
	
	public Usuario() {
		
	}
	
	public Usuario(String nombre) {
		Nombre = nombre;
	}	
	
	public int getId() {
		return Id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}	
}
