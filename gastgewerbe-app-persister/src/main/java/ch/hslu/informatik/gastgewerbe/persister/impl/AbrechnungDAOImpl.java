package ch.hslu.informatik.gastgewerbe.persister.impl;

import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.persister.AbrechnungDAO;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AbrechnungDAOImpl extends GenericPersisterDAOImpl<Abrechnung> implements AbrechnungDAO {

    public AbrechnungDAOImpl(){
        super(Abrechnung.class);
    }

    @Override
    public List<Abrechnung> findByDatum(LocalDateTime zeit) throws Exception {

        // Datum von
        LocalDateTime startDatum = zeit;

        // Datum bis
        LocalDateTime endDatum = zeit.plusDays(1);

        EntityManager em = JPAUtil.createEntityManager();

        TypedQuery<Abrechnung> query = em.createNamedQuery("Abrechnung.findByDatum", Abrechnung.class);

        query.setParameter("startDatum", startDatum);
        query.setParameter("endDatum", endDatum);

        List<Abrechnung> liste = query.getResultList();

        em.close();

        return liste != null ? liste : new ArrayList<Abrechnung>();
    }

    @Override
    public List<Abrechnung> findByBenutzer(Benutzer benutzer) throws Exception {
        EntityManager em = JPAUtil.createEntityManager();

        TypedQuery<Abrechnung> query = em.createNamedQuery("Abrechnung.findByBenutzer", Abrechnung.class);

        query.setParameter("benutzer", benutzer);

        List<Abrechnung> liste = query.getResultList();

        em.close();

        return liste != null ? liste : new ArrayList<Abrechnung>();
    }

    @Override
    public List<Abrechnung> findByBenutzerUndDatum(Benutzer benutzer, LocalDateTime zeit) throws Exception {
        // Datum von
        LocalDateTime startDatum = zeit;

        // Datum bis
        LocalDateTime endDatum = zeit.plusDays(1);

        EntityManager em = JPAUtil.createEntityManager();

        TypedQuery<Abrechnung> query = em.createNamedQuery("Abrechnung.findByBenutzerUndDatum", Abrechnung.class);

        query.setParameter("benutzer", benutzer);
        query.setParameter("startDatum", startDatum);
        query.setParameter("endDatum", endDatum);

        List<Abrechnung> liste = query.getResultList();

        em.close();

        return liste != null ? liste : new ArrayList<Abrechnung>();
    }
}
