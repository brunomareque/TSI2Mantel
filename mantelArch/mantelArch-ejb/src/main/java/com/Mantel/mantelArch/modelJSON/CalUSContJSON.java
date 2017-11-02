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
public final class CalUSContJSON {
	
	public ObjectId id;
	public String Titulo;
	public String Username;
	public boolean Usado;
	public boolean Voto;
	
	public ObjectId getId() {
		return this.id;
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
	public String getUsername() {
		return this.Username;
	}
	public void setUsername(String Username) {
		this.Username = Username;
	}
	public boolean isUsado() {
		return this.Usado;
	}
	public void setUsado(boolean Usado) {
		this.Usado = Usado;
	}
	public boolean isVoto() {
		return this.Voto;
	}
	public void setVoto(boolean Voto) {
		this.Voto = Voto;
	}
	
	public CalUSContJSON( String Titulo, String Username, boolean Usado, boolean Voto) {
		this.Titulo = Titulo;
		this.Username = Username;
		this.Usado = Usado;
		this.Voto = Voto;
	}
	public CalUSContJSON() {
		
	}
	
	
	

}
