package db;

import datamodels.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserAdder800 {

	public static User findByUsername(String username) {
		String query = "SELECT u FROM User u WHERE u.login = :username";
		TypedQuery<User> typedQuery = DBManager.getEntityManager().createQuery(query, User.class);
		typedQuery.setParameter("username", username);
		return typedQuery.getResultStream().findFirst().orElse(null);
	}

	public static void saveUser(User user) {
		EntityManager em = DBManager.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		}
	}

	public static void deleteUser(User user) {
		EntityManager em = DBManager.getEntityManager();
		em.getTransaction().begin();
		em.remove(DBManager.getEntityManager().merge(user));
		em.getTransaction().commit();
	}

}