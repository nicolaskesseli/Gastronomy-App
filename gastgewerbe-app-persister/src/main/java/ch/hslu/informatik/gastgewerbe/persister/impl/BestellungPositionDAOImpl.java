package ch.hslu.informatik.gastgewerbe.persister.impl;

import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
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

    @Override
    public List<BestellungPosition> findByBereit(Boolean bestellungBereit) throws Exception {

        EntityManager em = JPAUtil.createEntityManager();

        TypedQuery<BestellungPosition> query = em.createNamedQuery("BestellungPosition.findByBereit", BestellungPosition.class);

        query.setParameter("bestellungBereit", bestellungBereit);

        List<BestellungPosition> liste = query.getResultList();

        em.close();

        return liste != null ? liste : new ArrayList<BestellungPosition>();
    }

    @Override
    public List<BestellungPosition> findByAusgeliefert(Boolean bestellungAusgeliefert) throws Exception {

        EntityManager em = JPAUtil.createEntityManager();

        TypedQuery<BestellungPosition> query = em.createNamedQuery("BestellungPosition.findByAusgeliefert", BestellungPosition.class);

        query.setParameter("bestellungAusgeliefert", bestellungAusgeliefert);

        List<BestellungPosition> liste = query.getResultList();

        em.close();

        return liste != null ? liste : new ArrayList<BestellungPosition>();
    }
}
