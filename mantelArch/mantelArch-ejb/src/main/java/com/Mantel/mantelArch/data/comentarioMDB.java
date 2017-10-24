package com.Mantel.mantelArch.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import com.Mantel.mantelArch.model.comentario;
public class comentarioMDB {
	
	 private static comentarioMDB mDbSingleton; 
	 
	public static comentarioMDB getInstance(){
		  if(mDbSingleton == null){
		   mDbSingleton = new comentarioMDB();
		  }
		  return mDbSingleton;
		 } 
	
	 	
	public void insertarComentario(comentario coment) {
		
		MongoClient mongo = conectar();
		MongoDatabase database = mongo.getDatabase("mantel");
		MongoCollection<Document> collection = database.getCollection(coment.getTipo());
		
		Document doc = new Document("comentario", coment.getComentario())
				
				
                .append("usuario", coment.getUsuario())
                .append("fecha", coment.getFecha())
                .append("spoiler",coment.isSpoiler() )
                .append("video", coment.getVideo());
		
		collection.insertOne(doc);

	}
	public void modificarComentario(comentario coment) {
		MongoClient mongo = conectar();
		MongoDatabase database = mongo.getDatabase("mantel");
		MongoCollection<Document> collection = database.getCollection(coment.getTipo());
		
		Document doc = new Document("comentario", coment.getComentario())
                .append("usuario", coment.getUsuario())
                .append("fecha", coment.getFecha())
                .append("spoiler",coment.isSpoiler() )
                .append("video", coment.getVideo());
		
		ObjectId id = new ObjectId(coment.getId());
		collection.replaceOne(Filters.eq("_id", id), doc);
	}
	public void eliminarComentario(comentario coment) {
		MongoClient mongo = conectar();
		MongoDatabase database = mongo.getDatabase("mantel");
		MongoCollection<Document> collection = database.getCollection(coment.getTipo());
		
		ObjectId id = new ObjectId(coment.getId());
		collection.deleteOne(Filters.eq("_id", id));
	}
	public List<comentario> listarComentario(String titulo, String tipo) {
		
		MongoClient mongo = conectar();
		MongoDatabase database = mongo.getDatabase("mantel");
		MongoCollection<Document> collection = database.getCollection(tipo);
		
		List<Document> comentaux = new ArrayList<Document>();
		comentaux =  (List<Document>) collection.find(Filters.eq("titulo", titulo)).into( new ArrayList<Document>());
		
		List<comentario> coment = new ArrayList<comentario>();
		
		for (Document com : comentaux) {
			
			comentario aux = new comentario();
			
			ObjectId id = com.getObjectId("_id");
			aux.setId(id.toHexString());
			aux.setComentario(com.getString("comentario"));
			aux.setFecha(com.getDate("fecha"));
			aux.setSpoiler(com.getBoolean("spoiler", false));
			aux.setTipo(com.getString("tipo"));
			aux.setUsuario(com.getString("usuario"));
			aux.setVideo(com.getString("titulo"));
			
			coment.add(aux);
		}
		return coment;
	}
	public List<comentario> listarComentarioUsu(String usuario,String titulo, String tipo) {
		MongoClient mongo = conectar();
		MongoDatabase database = mongo.getDatabase("mantel");
		MongoCollection<Document> collection = database.getCollection(tipo);
		
		List<Document> comentaux = new ArrayList<Document>();
		comentaux =  (List<Document>) collection.find(Filters.and(Filters.eq("titulo",titulo),Filters.eq("usuario", titulo))).into( new ArrayList<Document>());
		
		List<comentario> coment = new ArrayList<comentario>();
		
		for (Document com : comentaux) {
			
			comentario aux = new comentario();
			
			ObjectId id = com.getObjectId("_id");
			aux.setId(id.toHexString());
			aux.setComentario(com.getString("comentario"));
			aux.setFecha(com.getDate("fecha"));
			aux.setSpoiler(com.getBoolean("spoiler", false));
			aux.setTipo(com.getString("tipo"));
			aux.setUsuario(com.getString("usuario"));
			aux.setVideo(com.getString("titulo"));
			
			coment.add(aux);
		}
		return coment;
	}
	
	public void marcarSpoiler(comentario coment) {
		MongoClient mongo = conectar();
		MongoDatabase database = mongo.getDatabase("mantel");
		MongoCollection<Document> collection = database.getCollection(coment.getTipo());
		
		Document doc = new Document("comentario", coment.getComentario())
                .append("usuario", coment.getUsuario())
                .append("fecha", coment.getFecha())
                .append("spoiler",true )
                .append("video", coment.getVideo());
		
		ObjectId id = new ObjectId(coment.getId());
		collection.replaceOne(Filters.eq("_id", id), doc);
	}
	public MongoClient conectar() {
		
		MongoClientURI connectionString = new MongoClientURI("mongodb://admin:adminmantel2017@ds121345.mlab.com:21345/mantel");
		MongoClient mongoClient = new MongoClient(connectionString);
		return mongoClient;
	}
	
	
}
