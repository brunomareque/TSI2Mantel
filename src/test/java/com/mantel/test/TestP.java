package com.mantel.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class TestP {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mantel");
		EntityManager em = emf.createEntityManager();

		Usuario user = new Usuario("Baladino");
		
		try {
			em.getTransaction().begin();
			//em.persist(user);			
		    List<Usuario> listPersons = em.createQuery("SELECT u FROM Usuario AS u").getResultList();
			listPersons.forEach(u -> System.out.println(u.getNombre()));
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			e.getMessage();
		}
		System.out.println("Atualizado com sucesso!");

		em.close();
	}

}
