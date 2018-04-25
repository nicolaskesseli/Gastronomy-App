package ch.hslu.informatik.gastgewerbe.persister.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JPAUtil {
	private static Logger logger = LogManager.getLogger(JPAUtil.class);

	private static EntityManagerFactory entityManagerFactory = null;

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("gastgewerbe-pu");
		} catch (Throwable e) {
			logger.error("Error: ",  e);
			throw new RuntimeException(e);
		}
	}

	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public static EntityManager createEntityManagerForDelition() {
		return Persistence.createEntityManagerFactory("delete-gastgewerbe-pu").createEntityManager();
	}
}
