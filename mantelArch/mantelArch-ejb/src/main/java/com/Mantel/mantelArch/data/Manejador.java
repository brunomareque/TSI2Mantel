package com.Mantel.mantelArch.data;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;
import java.util.Arrays;
import org.bson.Document;
import java.util.ArrayList;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


@SuppressWarnings({ "resource", "unused" })
public class Manejador {
	
	
	public ArrayList<String> GetTitulos() {
		
		try {
			// IMPORTANTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
					fromProviders(PojoCodecProvider.builder().build()));

			String con = ConexionBDTitulos();
			MongoClientURI connectionString = new MongoClientURI(con);
			MongoClient mongoClient = new MongoClient(connectionString);
			MongoDatabase database = mongoClient.getDatabase("titulos-contenido");
			database = database.withCodecRegistry(pojoCodecRegistry);

			MongoCollection<Document> col = database.getCollection("Titulos");
			
			ArrayList<String> ar = new ArrayList<String>();
			 
			MongoCursor<Document> cursor = col.find().iterator();
			try {
			    while (cursor.hasNext()) {
			        ar.add(cursor.next().toJson());
			    }
			} finally {
			    cursor.close();
			}

			
			return ar;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	// BD DE LAS CALIFICACIONES POR USUARIO
		public String ConexionBDTitulos() {
			String con = "mongodb://admin:admin@ds013918.mlab.com:13918/titulos-contenido";
			return con;
		}

	

}
