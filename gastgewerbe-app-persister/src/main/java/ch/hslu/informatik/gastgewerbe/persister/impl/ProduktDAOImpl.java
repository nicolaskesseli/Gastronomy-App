package ch.hslu.informatik.gastgewerbe.persister.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.persister.ProduktDAO;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;

public class ProduktDAOImpl extends GenericPersisterDAOImpl<Produkt> implements ProduktDAO {

    private static final Logger logger = LogManager.getLogger(ProduktDAOImpl.class);

    public ProduktDAOImpl() {
        super(Produkt.class);
    }

    public List<Produkt> findByName(String name) throws Exception {

        EntityManager em = JPAUtil.createEntityManager();

        TypedQuery<Produkt> query = em.createNamedQuery("ProduktTyp.findByName", Produkt.class);

        query.setParameter("name", name);

        List<Produkt> liste = query.getResultList();

        em.close();

        return liste != null ? liste : new ArrayList<Produkt>();
    }

    public Produkt findByTypCode(String typCode) throws Exception {

        EntityManager em = JPAUtil.createEntityManager();

        TypedQuery<Produkt> query = em.createNamedQuery("ProduktTyp.findByTypCode", Produkt.class);

        query.setParameter("typCode", typCode);

        List<Produkt> liste = query.getResultList();

        em.close();

        if (liste.isEmpty()) {
            return null;
        } else if (liste.size() == 1) {
            return liste.get(0);
        } else {
            String message = "Mehr als eine ProduktTyp-Entity mit dem Code \'" + typCode + "\' gefunden";
            logger.error(message);
            throw new IllegalStateException(message);
        }
    }

	
	public Produkt findByProduktId(int produktId) throws Exception {

		EntityManager em = JPAUtil.createEntityManager();
		
		 TypedQuery<Produkt> query = em.createNamedQuery("Produkt.FindByProduktId", Produkt.class);
		 
		 query.setParameter("ProduktId", produktId);
		 
		 List<Produkt> liste = query.getResultList();

	        em.close();

	        if (liste.isEmpty()) {
	            return null;
	        } else if (liste.size() == 1) {
	            return liste.get(0);
	        } else {
	            String message = "Mehr als ein Produkt mit der ProduktId \'" + produktId + "\' gefunden";
	            logger.error(message);
	            throw new IllegalStateException(message);
	        }
		
		
	}

	
	public List<Produkt> findByKategorie(String kategorie) throws Exception {

		 EntityManager em = JPAUtil.createEntityManager();

	        TypedQuery<Produkt> query = em.createNamedQuery("Produkt.findByKategorie", Produkt.class);

	        query.setParameter("kategorie", kategorie);

	        List<Produkt> liste = query.getResultList();

	        em.close();

	        return liste != null ? liste : new ArrayList<Produkt>();
		
		
	}

    

}
