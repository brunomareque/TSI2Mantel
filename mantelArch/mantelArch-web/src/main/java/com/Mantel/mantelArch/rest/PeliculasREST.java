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
import javax.ws.rs.core.MediaType; 
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.Mantel.mantelArch.data.ManejadorPeliculas;


@SuppressWarnings("unused")
@Path("/peliculas")
public class PeliculasREST {

	ManejadorPeliculas mp = new ManejadorPeliculas();
	PeliculasPOCO pp = new PeliculasPOCO();
	
	@GET
	@Path("/prueba")
	public String probar() {
		return "PELICULAS REST FUNCIONANDO DEBAJDSBASBDABSDJABSD";
	}
	
	
	 @GET
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    @Path("/foo")
	    public GenericEntity<List<String>> stringlist()
	    {
	      List<String> list = Arrays.asList("test", "as");

	      return new GenericEntity<List<String>>(list) {};
	    }
	
	@GET
	@Path("/tetulos")
	public String titulos() {
		return "TETULOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOS";
	}
	
	
	@XmlRootElement
	public class PeliculasPOCO{
		private String Nombre;

		public PeliculasPOCO(String Nombre) {
			
		}

		public PeliculasPOCO() {
			
		}
		public String getNombre() {
			return Nombre;
		}

		public void setNombre(String Nombre) {
			this.Nombre = Nombre;
		}
		
	}
	
	@XmlRootElement
	public class JaxbString {

	    private String value;

	    public JaxbString(){}

	    public JaxbString(String v){
	        this.setValue(v);
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

	    @XmlElement
	    public String getValue() {
	        return value;
	    }
	}
}
