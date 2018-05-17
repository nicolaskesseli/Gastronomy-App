package ch.hslu.informatik.gastgewerbe.persister.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.Person;
import ch.hslu.informatik.gastgewerbe.model.Tisch;
import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;

public class BestellungDAOImpl extends GenericPersisterDAOImpl<Bestellung> implements BestellungDAO {

	private static final Logger logger = LogManager.getLogger(BestellungDAOImpl.class);

	public BestellungDAOImpl() {
		super(Bestellung.class);
	}

	public List<Bestellung> findByTischNr(int TischNr) throws Exception {

		EntityManager em = JPAUtil.createEntityManager();

		TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByTischNr", Bestellung.class);

		query.setParameter("tischNr", TischNr);

		List<Bestellung> liste = query.getResultList();

		em.close();

		if (liste.isEmpty()) {
			String message = "Keine Bestellung mit der Tisch-Nr. " + TischNr + " gefunden";
			logger.error(message);
			throw new IllegalStateException(message);

		} else {
			return liste;

		}

	}

	public List<Bestellung> findByZeit (LocalDateTime zeit) throws Exception{

		EntityManager em = JPAUtil.createEntityManager();

		TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByZeit", Bestellung.class);

		query.setParameter("zeit", zeit);

		List<Bestellung> liste = query.getResultList();

		em.close();

		return liste != null ? liste : new ArrayList<Bestellung>();
	}

	@Override
	public List<Bestellung> findByRechBezahlt(Boolean rechnungBezahlt) throws Exception {

		EntityManager em = JPAUtil.createEntityManager();

		TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByRechBezahlt", Bestellung.class);

		query.setParameter("rechnungBezahlt", rechnungBezahlt);

		List<Bestellung> liste = query.getResultList();

		em.close();

		return liste != null ? liste : new ArrayList<Bestellung>();
	}

	@Override
	public List<Bestellung> findByBereit(Boolean bestellungBereit) throws Exception {

		EntityManager em = JPAUtil.createEntityManager();

		TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByBereit", Bestellung.class);

		query.setParameter("bestellungBereit", bestellungBereit);

		List<Bestellung> liste = query.getResultList();

		em.close();

		return liste != null ? liste : new ArrayList<Bestellung>();
	}

	@Override
	public List<Bestellung> findByAusgeliefert(Boolean bestellungAusgeliefert) throws Exception {

		EntityManager em = JPAUtil.createEntityManager();

		TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByAusgeliefert", Bestellung.class);

		query.setParameter("bestellungAusgeliefert", bestellungAusgeliefert);

		List<Bestellung> liste = query.getResultList();

		em.close();

		return liste != null ? liste : new ArrayList<Bestellung>();
	}

	@Override
	public List<Bestellung> findByRechBezahltTisch(int TischNr, Boolean rechnungBezahlt) throws Exception {
		EntityManager em = JPAUtil.createEntityManager();

		TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByRechBezahltTisch", Bestellung.class);

		query.setParameter("tischNr", TischNr);
		query.setParameter("rechnungBezahlt", rechnungBezahlt);

		List<Bestellung> liste = query.getResultList();

		em.close();

		return liste != null ? liste : new ArrayList<Bestellung>();
	}
	


}
