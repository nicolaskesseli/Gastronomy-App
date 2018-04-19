package ch.hslu.informatik.gastgewerbe.persister.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.persister.ProduktTypDAO;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;

public class ProduktTypDAOImpl extends GenericPersisterDAOImpl<Produkt> implements ProduktTypDAO {

    private static final Logger logger = LogManager.getLogger(ProduktTypDAOImpl.class);

    public ProduktTypDAOImpl() {
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

    

}
