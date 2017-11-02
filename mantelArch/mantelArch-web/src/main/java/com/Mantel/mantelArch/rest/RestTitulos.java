package com.Mantel.mantelArch.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.Mantel.mantelArch.data.Manejador;


@Path("/titulos")
public class RestTitulos {
	
	Manejador m = new Manejador();
	
	@GET
	@Path("/prueba")
	public String probar() {
		return "ESTO FUNCA CHE";
	}
	
	@GET
	@Path("/GetTitulos")
	public String GetTitulos(){
		try {
			ArrayList<String> ret = m.GetTitulos();
			return ret.toString();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
