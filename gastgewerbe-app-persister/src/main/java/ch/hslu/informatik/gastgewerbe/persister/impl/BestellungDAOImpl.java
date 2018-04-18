package ch.hslu.informatik.gastgewerbe.persister.impl;

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

	@Override
	public Bestellung findByProduktId(int ProduktId) throws Exception {
		
		EntityManager em = JPAUtil.createEntityManager();
		
		TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByProduktId", Bestellung.class);
		
		query.setParameter("ProduktId", ProduktId);
		
		List<Bestellung> liste = query.getResultList();
		
		em.close();
		
		if(liste.isEmpty()) {
			String message = "Ausserhalb des Gültigkeitsbereichs";
		}
	}

	@Override
	public Bestellung findByProduktBezeichnung(String bezeichnung) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bestellung findByProduktKategorie(String kategorie) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
