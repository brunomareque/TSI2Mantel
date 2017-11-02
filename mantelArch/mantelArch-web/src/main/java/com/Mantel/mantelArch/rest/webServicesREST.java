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

import com.Mantel.mantelArch.data.ManejadorPeliculas;
import com.Mantel.mantelArch.data.ManejadorUsuario;
import com.Mantel.mantelArch.model.User;


@SuppressWarnings("unused")
@Path("/contenido")
public class webServicesREST {

	ManejadorUsuario mu = new ManejadorUsuario();
	ManejadorPeliculas mp = new ManejadorPeliculas();
	
	UsuarioPOCO up = new UsuarioPOCO();
	
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("login-logout");
//    EntityManager em = emf.createEntityManager();
//	
    @GET
    @Path("/video")
    @Produces("video/mp4")
    public Response video() {
        File file = new File("C:\\Users\\Admin\\Downloads\\Juuni_Taisen_Opening_Panorama_Panama_Town_Rapture(youtube.com).mp4");
        return Response.ok(file,"video/mp4")
                .build();
    }
    @GET
    @Path("/GetUltimoSegundo/{Username}&{Titulo}")
    public Response GetUltimoSegundo(@PathParam("Username") String Username,@PathParam("Titulo") String Titulo) {
    	try {
    		int res = mp.GetUltimoSegundo(Username, Titulo);
    		 return Response.ok(res).build();
    	}catch(Exception e) {
    		  return Response.serverError().build();
    		
    	}
    }
    
    @POST
    @Path("/ultimoSeg/{Username}&{Titulo}&{Segundos}")
    public Response UltimoSeg(@PathParam("Username") String Username,@PathParam("Titulo") String Titulo,@PathParam("Segundos") int Segundos) {
    	try {
    		boolean resp = mp.PostSegundos(Username, Titulo, Segundos);
    		return Response.ok(resp).build();
    	}catch(Exception e) {
    		return Response.serverError().build();
    	}
    }
    
    
    @GET
    @Path("/cont/{nombre}")
    @Produces("video/mp4")
    public Response videoPorNombre(@PathParam("nombre") String nombre) {
    	try {
    		//Esto es para luego 
    		/*
    		 @Path("/cont/{categoria}/{nombre}")
    		  categoria puede ser, pelicula,serie o evento ppv, tonces el contenido lo divido en las carpetas, menos el ppv que hay que ver como hacer
    		 */
    		String dirCarpeta = "C:\\Users\\Admin\\Downloads\\";
    		String extension =".mp4";
    		
    		 File file = new File(dirCarpeta+nombre+extension);
    	        return Response.ok(file,"video/mp4")
    	                .build();
    	        
    	}catch(Exception e) {
    		return Response.serverError().build();
    	}
       
    }
    
   
    

    
    //no viene funcando bien, pa arreglar
	@GET
	@Path("/getUsuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsuarios(){
		try{
			
			List<User> lista = new ArrayList<User>();
			 lista = mu.getUsuarios();
			
			 return "PRUEBA USUARIOS";
			
		}catch(Exception e) {
			return null;
		
		}
	}
	
	//FUNCA
	@POST
	@Path("/alta/{password}/{username}/{name}/{lastname}/{email}/{creationdate}/{roleid}")
	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response postAlta(@PathParam("password") String password,@PathParam("username") String username, @PathParam("name") String name,@PathParam("lastname") String lastname,@PathParam("email") String email, @PathParam("creationdate") Date creationdate,@PathParam("roleid") int roleid) 
	{
		try {
			String output = "Usuario"+username+" dado de ALTA CORRECTAMENTE" ;
			
			 boolean respuesta = mu.Alta(password, username, name, lastname, email, creationdate, roleid);  
			 if(respuesta)
				 return Response.status(200).entity(output).build();
			 else {
				 String outputelse = "Usuario"+username+" ya existe, NO SE PUDO DAR DE ALTA" ;
				 return Response.status(200).entity(outputelse).build();
			 }
				 
		}catch(Exception e) {
			e.printStackTrace();
			String output = "Usuario"+username+" NO SE PUDO DAR DE ALTA" ;
			return Response.status(500).entity(output).build();	
		}
		
	}
	
	//FUNCA
	@PUT
	@Path("/update/{password}/{username}/{name}/{lastname}/{email}/{creationdate}/{roleid}")
	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(@PathParam("password") String password,@PathParam("username") String username, @PathParam("name") String name,@PathParam("lastname") String lastname,@PathParam("email") String email, @PathParam("creationdate") Date creationdate,@PathParam("roleid") int roleid) {
		try {
			
			
			User u = new User();
			u.setCreationdate(creationdate);
			u.setEmail(email);
			u.setLastname(lastname);
			u.setName(name);
			u.setPassword(password);
			u.setRole(roleid);
			u.setUsername(username);
			
			String output = "Usuario "+username+" MODIFICADO CORRECTAMENTE" ;
			
			mu.Modificaion(u);
			 
			return Response.status(200).entity(output).build();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			String outputelse = "Usuario "+username+"  NO SE PUDO MODIFICAR CORRECTAMENTE" ;
			return Response.status(500).entity(outputelse).build();
			
		}
	}
	
	//FUNCA
	@DELETE
    @Path("/{usename}")
	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response delete(@PathParam("usename") String username) {
		
        
        try 
        {
        	String output = "Usuario "+username+" dado de BAJA CORRECTAMENTE" ;
        	boolean respuesta = mu.Baja(username);
        	if(respuesta)
        		return Response.status(200).entity(output).build();
        	else {
        		 String outputelse = "Usuario "+username+"  NO SE PUDO DAR DE BAJA" ;
				 return Response.status(200).entity(outputelse).build();
        	}
        	//return "Usuario Dado de baja correctamente";
        	
        }catch(Exception e) 
        {
        	e.printStackTrace(); 
        	String output = "Usuario "+username+"  NO SE PUDO DAR DE BAJA" ;
			return Response.status(500).entity(output).build();
        	//return "Error al dar de baja  Usuario";
        }
        
	}
	
	//---------------LA CHUCHA PARA DEVOLVER LA SESION---------------------------
	@GET
	@Path("/sesion")
	public String obtenerDatosEnSesion( ContainerRequestContext requestContext) {
		Cookie cookie = requestContext.getCookies().get("JSESSIONID");
		String value = cookie.getValue();
		
		return value;
	}
	//---------------LA CHUCHA PARA DEVOLVER LA SESION---------------------------
	
	//Para probar de one si ta corriendo bien
	@GET
	@Path("/prueba")
	public String probar() {
		return "ESTO FUNCA CHE";
	}
	
	@XmlRootElement
	public class UsuarioPOCO{
		
		private String username;
		private Date creationdate;
		private String email;
		private String lastname;
		private String name;
		private String password;
		private int roleid;
		public UsuarioPOCO(String string, String string2, String string3, String string4, String string5, Date e,
				int i) {
			// TODO Auto-generated constructor stub
		}
		public UsuarioPOCO() {
			// TODO Auto-generated constructor stub
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Date getCreationdate() {
			return creationdate;
		}
		public void setCreationdate(Date creationdate) {
			this.creationdate = creationdate;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getRoleid() {
			return roleid;
		}	
		public void setRoleid(int roleid) {
			this.roleid = roleid;
		}	
	}
	
	
}
