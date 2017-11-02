package com.Mantel.mantelArch.service;

import java.sql.Date;

import com.Mantel.mantelArch.model.User;



public interface InterfazUsuario {
	public boolean Alta(String password,String username,String name,String lastname,String email,Date creationdate,int roleid);
	public boolean Baja(String username);
	public boolean Modificaion(User u);

}
