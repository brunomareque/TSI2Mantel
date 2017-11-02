package com.Mantel.mantelArch.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Cookie;

import javax.ws.rs.container.ContainerRequestContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.Mantel.mantelArch.data.ManejadorCalificaciones;


@SuppressWarnings("unused")
@Path("/calificaciones")
public class CalificacionesREST {

	ManejadorCalificaciones mc = new ManejadorCalificaciones();

	// Para probar de one si ta corriendo bien
	@GET
	@Path("/prueba")
	public String probar() {
		return "ESTO FUNCA CHE";
	}

	@GET
	@Path("/getCalPeli/{Titulo}")
	public int getCalPeli(@PathParam("Titulo") String Titulo) {
		try {
			int resultado;
			resultado = mc.GetCalPelicula(Titulo);
			return resultado;
		} catch (Exception e) {
			return 666;
		}
	}
	
	@GET
	@Path("/getMyCal/{Titulo}&{Username}")
	public int getMyCal(@PathParam("Titulo") String Titulo,@PathParam("Username") String Username) {
		try {
			int resultado;
			resultado = mc.GetMyCal(Titulo, Username);
			return resultado;
		} catch (Exception e) {
			return 666;
		}
	}

	@PUT
	@Path("/SetMGPelicula/{Titulo}&{Username}")
	public Response SetMGPelicula(@PathParam("Titulo") String Titulo,@PathParam("Username") String Username) {
		try {

			String output = "Gracias por calificar este titulo!";
			boolean respuesta = mc.SetMGPelicula(Titulo,Username);
			if (respuesta)
				return Response.status(200).entity(output).build();
			else {
				String outputelse = "No es posible calificar este titulo en este momento,intentar mas tarde.";
				return Response.status(500).entity(outputelse).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			String output = "No es posible calificar este titulo en este momento,intentar mas tarde.";
			return Response.status(500).entity(output).build();
		}
	}
	
	@PUT
	@Path("/SetNOMGPelicula/{Titulo}&{Username}")
	public Response SetNOMGPelicula(@PathParam("Titulo") String Titulo,@PathParam("Username") String Username) {
		try {

			String output = "Gracias por calificar este titulo! Lamentamos que no haya sido de su agrado, continue viendo mas contenido!";
			boolean respuesta = mc.SetNOMGPelicula(Titulo,Username);
			if (respuesta)
				return Response.status(200).entity(output).build();
			else {
				String outputelse = "No es posible calificar este titulo en este momento,intentar mas tarde.";
				return Response.status(500).entity(outputelse).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			String output = "No es posible calificar este titulo en este momento,intentar mas tarde.";
			return Response.status(500).entity(output).build();
		}
	}

}
