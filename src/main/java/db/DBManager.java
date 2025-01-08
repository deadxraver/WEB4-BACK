package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBManager {
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
	private static EntityManager em = entityManagerFactory.createEntityManager();

	public static EntityManager getEntityManager() {
		if (em == null || !em.isOpen()) {
			em = entityManagerFactory.createEntityManager();
		}
		return em;
	}
}
