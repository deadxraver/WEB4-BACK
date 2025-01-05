package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UserAdder800 {
	private final EntityManagerFactory entityManagerFactory;
	EntityManager em;


	public UserAdder800() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("Users");
		this.em = entityManagerFactory.createEntityManager();
	}

	public EntityManager getEntityManager() {
		if (em == null || !em.isOpen()) {
			em = entityManagerFactory.createEntityManager();
		}
		return em;
	}

	public User findByUsername(String username) {
		try {
			String query = "SELECT u FROM User u WHERE u.login = :username";
			TypedQuery<User> typedQuery = getEntityManager().createQuery(query, User.class);
			typedQuery.setParameter("username", username);
			return typedQuery.getResultStream().findFirst().orElse(null);
		} finally {
			getEntityManager().close();
		}
	}

	public void saveUser(User user) {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(user);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			getEntityManager().close();
		}
	}

	public void deleteUser(User user) {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(getEntityManager().merge(user));
			getEntityManager().getTransaction().commit();
		} finally {
			getEntityManager().close();
		}
	}

}