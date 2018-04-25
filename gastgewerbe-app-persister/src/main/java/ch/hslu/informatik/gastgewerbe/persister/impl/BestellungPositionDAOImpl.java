package ch.hslu.informatik.gastgewerbe.persister.impl;

import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.persister.BestellungPositionDAO;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BestellungPositionDAOImpl extends GenericPersisterDAOImpl<BestellungPosition> implements BestellungPositionDAO {

    public BestellungPositionDAOImpl() {
        super(BestellungPosition.class);
    }


    public List<BestellungPosition> findByProdukt(Produkt produkt) throws Exception {

        EntityManager em = JPAUtil.createEntityManager();
        TypedQuery<BestellungPosition> query = em.createNamedQuery("BestellungPosition.findByProdukt",
                BestellungPosition.class);
        query.setParameter("produkt", produkt);

        List<BestellungPosition> liste = query.getResultList();

        em.close();

        return liste != null ? liste : new ArrayList<BestellungPosition>();
    }
}
