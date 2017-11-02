package com.Mantel.mantelArch.data;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.Mantel.mantelArch.modelJSON.CalUSContJSON;
import com.Mantel.mantelArch.modelJSON.CalificacionPeliculasJSON;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;


import com.mongodb.client.model.Filters;

@SuppressWarnings("unused")
public class ManejadorCalificaciones {

	@SuppressWarnings("resource")
	public int GetCalPelicula(String Titulo) {
		try {

			String con = ConexionCalCont();
			MongoClientURI connectionString = new MongoClientURI(con);
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("clasificacion-contenido");


			MongoCollection<Document> col = database.getCollection("CalificacionPeliculas");

			Document aux = col.find(Filters.eq("Titulo", Titulo)).first();

			int mg = aux.getInteger("MeGusta");
			int total = aux.getInteger("MeGusta");

			int res = (mg * 100) / total;
			
			mongoClient.close();
			return res;

		} catch (Exception e) {
			e.printStackTrace();
			return 666;
		}

	}

	@SuppressWarnings("resource")
	public boolean SetMGPelicula(String Titulo,String Username) {
		try {

			String con = ConexionCalCont();
			MongoClientURI connectionString = new MongoClientURI(con);
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("clasificacion-contenido");

			MongoCollection<Document> col = database.getCollection("CalificacionPeliculas");


			Document aux = col.find(Filters.eq("Titulo", Titulo)).first();
			int mg = aux.getInteger("MeGusta");
			int total = aux.getInteger("Total");

			col.updateOne(Filters.eq("Titulo", Titulo), new Document("$set",
					new Document("Titulo", Titulo).append("MeGusta", mg + 1).append("Total", total + 1)));
			
			//Uso la otra func para meter el documento en la bd USUARIO-CALIFICACION
			setMGenBDUS(Titulo,Username);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("resource")
	public void setMGenBDUS(String Titulo,String Username) {

		try {
			
			String con = ConexionBDCalUsuariosContenido();
			MongoClientURI connectionString = new MongoClientURI(con);
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("calificacion-usuarios-contenido");

			MongoCollection<Document> col = database.getCollection("CalUsCont");
			
			Document docum = new Document("Titulo",Titulo)
					   .append("Username", Username)
		               .append("Usado", true)
		               .append("Voto",true);
			col.insertOne(docum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public boolean SetNOMGPelicula(String Titulo, String Username) {
		try {

			String con = ConexionCalCont();
			MongoClientURI connectionString = new MongoClientURI(con);
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("clasificacion-contenido");

			MongoCollection<Document> col = database.getCollection("CalificacionPeliculas");

			Document docu = col.find(Filters.eq("Titulo", Titulo)).first();
			int mg = docu.getInteger("MeGusta");
			int total = docu.getInteger("Total");

			col.updateOne(Filters.eq("Titulo", Titulo),
					new Document("$set", new Document("Titulo", Titulo).append("Total", total + 1)));

			setNOMGenBDUS( Titulo,Username);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("resource")
	public void setNOMGenBDUS(String Titulo,String Username) {

		try {
			
			String con = ConexionBDCalUsuariosContenido();
			MongoClientURI connectionString = new MongoClientURI(con);
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("calificacion-usuarios-contenido");
			
			MongoCollection<Document> col = database.getCollection("CalUsCont");
			
			Document docum = new Document("Titulo",Titulo)
					.append("Username", Username)
					.append("Usado", true)
					.append("Votado", true);
			col.insertOne(docum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public int GetMyCal(String Titulo, String Username) {
		try {
			
			String con = ConexionBDCalUsuariosContenido();
			MongoClientURI connectionString = new MongoClientURI(con);
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("calificacion-usuarios-contenido");

			MongoCollection<Document> col = database.getCollection("CalUsCont");

			Document comparar = new Document();
			comparar = col.find(Filters.and(Filters.eq("Titulo", Titulo), Filters.eq("Username", Username))).first();
			
			mongoClient.close();
			
			// no voto
			if (comparar.getBoolean("Usado", false) == false)
				return 0;
			// le gusto
			if ((comparar.getBoolean("Usado") == true) && (comparar.getBoolean("Voto")))
				return 1;
			// no le gusto
			if ((comparar.getBoolean("Usado") == true) && (!comparar.getBoolean("Voto")))
				return 5;
			// exit
			else
				return 9;
			

		} catch (Exception e) {
			return 666;
		}
	}
	// Funciones para las conexiones

	// BD DE LAS CALIFICACIONES POR USUARIO
	public String ConexionBDCalUsuariosContenido() {
		String con = "mongodb://admin:admin@ds125255.mlab.com:25255/calificacion-usuarios-contenido";
		return con;
	}

	// BD DE LAS CALIFICACIONES POR PELICULA
	public String ConexionCalCont() {
		String con = "mongodb://admin:admin@ds125255.mlab.com:25255/clasificacion-contenido";
		return con;
	}

}
