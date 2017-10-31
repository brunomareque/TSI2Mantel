package com.Mantel.mantelArch.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
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
		
		ObjectId id = new ObjectId(coment.getId());
		
		collection.updateOne((Filters.eq("_id", id )), new Document("$set", new Document("comentario", coment.getComentario())));
		
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

		
		List<comentario> coment = new ArrayList<comentario>();
		
		FindIterable<Document> iterable = collection.find(Filters.eq("video", titulo));
		for (Document com : iterable) {
			comentario aux = new comentario();
			
			ObjectId id = com.getObjectId("_id");
			aux.setId(id.toHexString());
			aux.setComentario(com.getString("comentario"));
			aux.setFecha(com.getDate("fecha"));
			aux.setSpoiler(com.getBoolean("spoiler", false));
			aux.setUsuario(com.getString("usuario"));
			aux.setVideo(com.getString("video"));
			
			coment.add(aux);
		}
		
	
		return coment;
	}
	public List<comentario> listarComentarioUsu(String usuario,String titulo, String tipo) {
		MongoClient mongo = conectar();
		MongoDatabase database = mongo.getDatabase("mantel");
		MongoCollection<Document> collection = database.getCollection(tipo);
			
		List<comentario> coment = new ArrayList<comentario>();
		
		FindIterable<Document> iterable = collection.find(Filters.and(Filters.eq("video",titulo),Filters.eq("usuario", usuario)));
		for (Document com : iterable) {
			comentario aux = new comentario();
			
			ObjectId id = com.getObjectId("_id");
			aux.setId(id.toHexString());
			aux.setComentario(com.getString("comentario"));
			aux.setFecha(com.getDate("fecha"));
			aux.setSpoiler(com.getBoolean("spoiler", false));
			aux.setUsuario(com.getString("usuario"));
			aux.setVideo(com.getString("video"));
			
			coment.add(aux);
		}
		
	
		return coment;
	}
	
	public void marcarSpoiler(String tipo , String ids) {
		MongoClient mongo = conectar();
		MongoDatabase database = mongo.getDatabase("mantel");
		MongoCollection<Document> collection = database.getCollection(tipo);
		
		ObjectId id = new ObjectId(ids);
		
		collection.updateOne((Filters.eq("_id", id )), new Document("$set", new Document("spoiler", false)));
				
		
	}
	public MongoClient conectar() {
		
		MongoClientURI connectionString = new MongoClientURI("mongodb://admin:adminmantel@ds121345.mlab.com:21345/mantel");
		MongoClient mongoClient = new MongoClient(connectionString);
		return mongoClient;
	}
	
	
}
