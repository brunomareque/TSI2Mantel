package com.Mantel.mantelArch.data;


import java.sql.Date;
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

import com.Mantel.mantelArch.model.User;
import com.Mantel.mantelArch.service.InterfazUsuario;

import java.sql.Date;


@SuppressWarnings("unused")
public class ManejadorUsuario implements InterfazUsuario {

	//Testear

	public boolean Alta(String password,String username,String name,String lastname,String email,Date creationdate,int roleid) 
	{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("login-logout");
        EntityManager em = emf.createEntityManager();
        
		try {
			java.sql.Date e = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			User u = new User();
			u.setName(name);
			u.setPassword(password);
			u.setUsername(username);
			u.setLastname(lastname);
			u.setEmail(email);
			u.setCreationdate(e);
			u.setRole(roleid);
			
			em.getTransaction().begin();
			
			em.persist(u);
			
			em.getTransaction().commit();
					
			em.close();
			
			return true;
			
		}catch(Exception e) {
			em.close();
			return false;
		}
	}

	public boolean Baja(String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("login-logout");
        EntityManager em = emf.createEntityManager();
        
        try 
        {
        	User u = em.find(User.class, username);
        	em.getTransaction().begin();
        	em.remove(u);
        	em.getTransaction().commit();
        	em.close();
        	return true;
        	
        }catch(Exception e) 
        {
        	em.close();
        	return false;
        }
        
		
	}


	public boolean Modificaion(User u) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("login-logout");
        EntityManager em = emf.createEntityManager();
        
        try 
        {
        				
			
        	User up = em.find(User.class, u.getUsername());
        	
        	up.setEmail(u.getEmail());
        	up.setPassword(u.getPassword());
        	up.setName(u.getName());
        	up.setEmail(u.getEmail());
        	up.setLastname(u.getLastname());
        	
        	//Toda la parte de la BD
        	em.getTransaction().begin();
        	//creo que este era para updatear
        	em.merge(up);
        	em.getTransaction().commit();
        	em.close();
        	
        	return true;
        	
        }catch(Exception e) 
        {
        	em.close();
        	return false;
        }
		
		
	}

	public User getUser(String username, String password) { 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("login-logout");
        EntityManager em = emf.createEntityManager();
		try 
		{ 
			User user = (User) em .createQuery( "SELECT u from User u where u.username = :username and u.password = :password") 
					.setParameter("username", username) 
					.setParameter("password", password)
					.getSingleResult(); 
			return user; 
		} catch (NoResultException e) 
		{ 
						return null;
		} 
	}

	public boolean Validar(String username, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("login-logout");
        EntityManager em = emf.createEntityManager();
		try 
		{	
			User val = em.find(User.class, username);
			//Toda la parte de la BD
        	em.getTransaction().begin();
        	em.getTransaction().commit();
        	em.close();
        	
			if(val.getPassword() == password) {
				return true;
			}
			return false;
		}catch(Exception e)
		{
			return false;
		}

		
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsuarios(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("login-logout");
        EntityManager em = emf.createEntityManager();
        
		try{
			Query query = em.createQuery("SELECT u FROM User u");
		    return (List<User>) query.getResultList();
			
		}catch(Exception e) {
			return null;
		
		}
	}
}
