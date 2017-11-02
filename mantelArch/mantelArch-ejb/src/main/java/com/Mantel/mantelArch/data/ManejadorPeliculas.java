package com.Mantel.mantelArch.data;




import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.Mantel.mantelArch.model.Peliculas;
import com.Mantel.mantelArch.modelJSON.UsuarioViJSON;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.sql.Date;;

@SuppressWarnings("unused")
public class ManejadorPeliculas {

	private static final Object Nombre = null;

	public Peliculas GetPrimerPelicula() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("login-logout");
		EntityManager em = emf.createEntityManager();

		try {
			Peliculas s = em.find(Peliculas.class, Nombre);
			return s;

		} catch (Exception e) {
			return null;

		}

	}

	@SuppressWarnings("resource")
	public boolean PostSegundos(String Username, String Titulo, int Segundos) {
		try {
			
			String con = ConexionBDUsuariosSegundos();
			MongoClientURI connectionString = new MongoClientURI(con);
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("usario-repro");

			MongoCollection<Document> col = database.getCollection("usuario-vi");

			Document docu = new Document();
			docu = col.find(Filters.and(Filters.eq("Titulo", Titulo), Filters.eq("Username", Username))).first();

			if (docu == null) {
				col.insertOne(new Document("Username",Username)
						.append("Titulo", Titulo)
						.append("Segundo", Segundos));
			} else {
				col.findOneAndUpdate(Filters.eq("Titulo", Titulo), new Document("$set",
						new Document("Username", Username)
						.append("Titulo", Titulo)
						.append("UltimoSeg", Segundos)));
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("resource")
	public int GetUltimoSegundo(String Username, String Titulo) {
		try {
			String con = ConexionBDUsuariosSegundos();
			MongoClientURI connectionString = new MongoClientURI(con);
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("usario-repro");

			MongoCollection<Document> col = database.getCollection("usuario-vi");

			Document docu = new Document();
			docu = col.find(Filters.and(Filters.eq("Titulo", Titulo), Filters.eq("Username", Username))).first();
			
			return docu.getInteger("UltimoSeg", 0);

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public String ConexionBDUsuariosSegundos() {
		String con = "mongodb://admin:admin@ds243285.mlab.com:43285/usario-repro";
		return con;
	}


	public List<String> GetListPeliculas() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("login-logout");
		EntityManager em = emf.createEntityManager();

		try {
			List<String> list = new ArrayList<String>();
			String uno = " aaaaa ";
			String dos = " bbbbbbb ";
			list.add(uno);
			list.add(dos);
			return list;

		} catch (Exception e) {
			return null;

		}

	}

}
