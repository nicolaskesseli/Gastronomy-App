package ch.hslu.informatik.gastgewerbe.businessprodukt;

import ch.hslu.informatik.gastgewerbe.api.ProduktService;
import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.persister.PersonDAO;
import ch.hslu.informatik.gastgewerbe.persister.ProduktDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.ProduktDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProduktManager implements ProduktService {

    private static Logger logger = LogManager.getLogger(ProduktManager.class);

    private ProduktDAO produktDAO;

    public ProduktDAO getProduktDAO() {

        if (produktDAO == null) {
            produktDAO = new ProduktDAOImpl();
        }

        return produktDAO;
    }

    @Override
    public Produkt produktHinzufuegen(Produkt produkt) throws Exception {
        try {
            return getProduktDAO().save(produkt);
        } catch (Exception e) {
            String msg = "Produkt \'" + produkt.getName() + " " + produkt.getBeschreibung()
                    + "\' konnte nicht hinzugefügt werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public void produktLoeschen(Produkt produkt) throws Exception {
        try {
            getProduktDAO().delete(produkt);
        } catch (Exception e) {
            String msg = "Produkt \'" + produkt.getName() + " " + produkt.getBeschreibung()
                    + "\' konnte nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Produkt produktAktualisieren(Produkt produkt) throws Exception {
        try {
            return getProduktDAO().update(produkt);
        } catch (Exception e) {
            String msg = "Produkt \'" + produkt.getName() + " " + produkt.getBeschreibung()
                    + "\' konnte nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Produkt> alleProdukt() throws Exception{
        try {
            return getProduktDAO().findAll();
        } catch (Exception e) {
            String msg = "Produkte konnten nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Produkt> findProduktByKategorie(KategorieTyp kategorieTyp) throws Exception{
        try {
            return getProduktDAO().findByKategorie(kategorieTyp);
        } catch (Exception e) {
            String msg = "Produkt als \'" + kategorieTyp.bezeichnung() + "\' konnten nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

	@Override
	public Produkt findByProduktCode(String produktCode) throws Exception {
        try {
            return getProduktDAO().findByProduktCode(produktCode);
        } catch (Exception e) {
            String msg = "Produkt als \'" + produktCode + "\' konnten nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
	}

	@Override
	public List<Produkt> findProduktByName(String name) throws Exception {
        try {
            return getProduktDAO().findByName(name);
        } catch (Exception e) {
            String msg = "Produkt \'" + name + "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
	}
}
