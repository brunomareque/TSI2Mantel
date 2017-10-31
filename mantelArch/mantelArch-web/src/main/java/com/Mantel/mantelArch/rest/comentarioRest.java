package com.Mantel.mantelArch.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Mantel.mantelArch.model.comentario;
import com.Mantel.mantelArch.data.comentarioMDB;

@Path("/comentario")
public class comentarioRest {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/insertar")
	public String insertar(comentario coment) {
		
		comentarioMDB dbSingleton = comentarioMDB.getInstance();
		dbSingleton.insertarComentario(coment);
		return "OK";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/modificar")
	public void modificarComentario(comentario coment) {
		comentarioMDB dbSingleton = comentarioMDB.getInstance();
		dbSingleton.modificarComentario(coment);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/eliminar")
	public void eliminarComentario(comentario coment) {
		comentarioMDB dbSingleton = comentarioMDB.getInstance();
		dbSingleton.eliminarComentario(coment);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listar/{tipo}/{titulo}")
	public List<comentario> listarComentario(@PathParam("titulo")String titulo,@PathParam("tipo") String tipo){
		comentarioMDB dbSingleton = comentarioMDB.getInstance();
		return dbSingleton.listarComentario(titulo, tipo);
		
	}
	

	@GET
	@Path("/prueba")
	public String prueba(){
		return "OK";
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	@Path("/Get/{usuario}/{tipo}/{titulo}")  
	public List<comentario> listarComentarioUsu(@PathParam("usuario") String usuario,@PathParam("titulo") String titulo,@PathParam("tipo") String tipo){
		
		comentarioMDB dbSingleton = comentarioMDB.getInstance();
		return dbSingleton.listarComentarioUsu(usuario, titulo, tipo);
	}
	
	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/spoiler/{tipo}/{id}")
	public void marcarSpoiler(@PathParam("tipo") String tipo, @PathParam("id") String id) {
		comentarioMDB dbSingleton = comentarioMDB.getInstance();
		dbSingleton.marcarSpoiler(tipo, id);
	}
}
