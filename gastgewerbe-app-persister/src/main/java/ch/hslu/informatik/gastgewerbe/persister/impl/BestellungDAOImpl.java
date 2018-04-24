package ch.hslu.informatik.gastgewerbe.persister.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;

public class BestellungDAOImpl extends GenericPersisterDAOImpl<Bestellung> implements BestellungDAO {

	private static final Logger logger = LogManager.getLogger(BestellungDAOImpl.class);

	public BestellungDAOImpl() {
		super(Bestellung.class);
	}

	public List<Bestellung> findByTischId(int TischId) throws Exception {

		EntityManager em = JPAUtil.createEntityManager();

		TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByTischId", Bestellung.class);

		query.setParameter("tischNr", TischId);

		List<Bestellung> liste = query.getResultList();

		em.close();

		if (liste.isEmpty()) {
			String message = "Keine Bestellung mit der Tisch-Nr. " + TischId + " gefunden";
			logger.error(message);
			throw new IllegalStateException(message);

		} else {
			return liste;

		}

	}

	public List<Bestellung> findByZeit (LocalDate zeit) throws Exception{

		EntityManager em = JPAUtil.createEntityManager();

		TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByZeit", Bestellung.class);

		query.setParameter("zeit", zeit);

		List<Bestellung> liste = query.getResultList();

		em.close();

		return liste != null ? liste : new ArrayList<Bestellung>();
	}

}
