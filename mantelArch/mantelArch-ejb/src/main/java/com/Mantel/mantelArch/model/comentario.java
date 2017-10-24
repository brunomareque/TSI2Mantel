package com.Mantel.mantelArch.model;

import java.util.Date;

public class comentario {
	
	private String id;
	private String comentario;
	private String usuario;
	private Date fecha;
	private boolean spoiler;
	private String video;
	private String tipo;
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isSpoiler() {
		return spoiler;
	}
	public void setSpoiler(boolean spoiler) {
		this.spoiler = spoiler;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}

}
