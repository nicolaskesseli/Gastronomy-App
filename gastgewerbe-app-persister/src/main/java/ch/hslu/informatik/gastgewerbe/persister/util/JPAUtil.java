package ch.hslu.informatik.gastgewerbe.persister.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = null;

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("gastgewerbe-pu");
		} catch (Throwable e) {
			throw new RuntimeException();
		}
	}

	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public static EntityManager createEntityManagerForDelition() {
		return Persistence.createEntityManagerFactory("delete-gastgewerbe-pu").createEntityManager();
	}
}
