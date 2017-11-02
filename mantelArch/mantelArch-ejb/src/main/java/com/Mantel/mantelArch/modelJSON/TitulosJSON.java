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

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@SuppressWarnings("unused")
public class TitulosJSON {
	
	public ObjectId id;
	public String Nombre;
	public String TipoCont;
	

    
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getTipoCont() {
		return TipoCont;
	}
	public void setTipoCont(String tipoCont) {
		TipoCont = tipoCont;
	}
	public TitulosJSON() {
		
	}
	public TitulosJSON(ObjectId id, String nombre, String tipoCont) {
		this.id = id;
		Nombre = nombre;
		TipoCont = tipoCont;
	}
	
	private List<String> prueba = new ArrayList<String>();

    public List<String> getPrueba()
    {
        return prueba;
    }

    public void setPersons(List<String> pruebas)
    {
        this.prueba = pruebas;
    }

	
	
}
