package ch.hslu.informatik.gastgewerbe.persister.impl;

import ch.hslu.informatik.gastgewerbe.model.Tisch;
import ch.hslu.informatik.gastgewerbe.persister.TischDAO;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TischDAOImpl extends GenericPersisterDAOImpl<Tisch> implements TischDAO {

    private static final Logger logger = LogManager.getLogger(TischDAOImpl.class);

    public TischDAOImpl() {
        super(Tisch.class);
    }

    public Tisch findByTischNr(int tischNr) throws Exception{

        EntityManager em = JPAUtil.createEntityManager();

        TypedQuery<Tisch> query = em.createNamedQuery("Tisch.findByTischNr", Tisch.class);

        query.setParameter("tischNr", tischNr);

        List<Tisch> liste = query.getResultList();

        em.close();

        if (liste.isEmpty()) {
            return null;
        } else if (liste.size() == 1) {
            return liste.get(0);
        } else {
            String message = "Mehr als ein Tisch-Entity mit Nummer \'" + tischNr + "\' gefunden";
            logger.error(message);
            throw new IllegalStateException(message);
        }
    }
}
