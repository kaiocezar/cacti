package br.com.onlance.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("onlance");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public static void closeEntityManagerFactory() {
		emf.close();
		
	}

}
