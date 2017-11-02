package com.Mantel.mantelArch.modelJSON;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@SuppressWarnings("unused")
public final class CalificacionPeliculasJSON {
	        
	
	public ObjectId id;
	public String Titulo;
	public int MeGusta;
	public int Total;
	
	
	
	public ObjectId getId() {
		return id;
	}



	public void setId(ObjectId id) {
		this.id = id;
	}



	public String getTitulo() {
		return this.Titulo;
	}



	public void setTitulo(String Titulo) {
		this.Titulo = Titulo;
	}



	public int getMeGusta() {
		return this.MeGusta;
	}



	public void setMeGusta(int MeGusta) {
		this.MeGusta = MeGusta;
	}



	public int getTotal() {
		return Total;
	}



	public void setTotal(int Total) {
		this.Total = Total;
	}



	public CalificacionPeliculasJSON() {
		
	}



	public CalificacionPeliculasJSON( String Titulo, int MeGusta, int Total) {
		this.Titulo = Titulo;
		this.MeGusta = MeGusta;
		this.Total = Total;
	}
	
	
	

}
