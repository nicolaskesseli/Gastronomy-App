package ch.hslu.informatik.gastgewerbe.persister.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.persister.AbrechnungDAO;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;

public class AbrechnungDAOImpl extends GenericPersisterDAOImpl<Abrechnung> implements AbrechnungDAO {

	private static final Logger logger = LogManager.getLogger(AbrechnungDAOImpl.class);

	public AbrechnungDAOImpl() {
		super(Abrechnung.class);
	}

	public List<Abrechnung> findByTischID(double TischId) throws Exception {

		EntityManager em = JPAUtil.createEntityManager();

		TypedQuery<Abrechnung> query = em.createNamedQuery("Abrechnung.findByTischID", Abrechnung.class);

		query.setParameter("bestellID", TischId);

		List<Abrechnung> liste = query.getResultList();

		em.close();

		if (liste.isEmpty()) {
			String message = "Keine Bestellung mit der Tisch-Nr. " + TischId + " gefunden";
			logger.error(message);
			throw new IllegalStateException(message);

		} else {
			return liste;

		}

	}

}
