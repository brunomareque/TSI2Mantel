package com.Mantel.mantelArch.model;

import java.util.Date;

public class ChatMessage {
	private String mensaje;
	private String usuario;
	private Date recibido;


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public Date getRecibido() {
		return recibido;
	}


	public void setRecibido(Date recibido) {
		this.recibido = recibido;
	}


	@Override
	public String toString() {
		return "ChatMessage [message=" + mensaje + ", sender=" + usuario
				+ ", received=" + recibido + "]";
	}
}
