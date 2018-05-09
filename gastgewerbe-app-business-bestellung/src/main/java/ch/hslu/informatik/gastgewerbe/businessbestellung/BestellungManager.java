package ch.hslu.informatik.gastgewerbe.businessbestellung;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.api.BestellungService;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BestellungDAOImpl;


public class BestellungManager implements BestellungService {
	
	
	
	private static Logger logger = LogManager.getLogger(BestellungManager.class);

    private BestellungDAO bestellungDAO;

    public BestellungDAO getBestellungDAO() {

        if (bestellungDAO == null) {
            bestellungDAO = new BestellungDAOImpl();
        }

        return bestellungDAO;
    }
	
	

	@Override
	public Bestellung bestellen(Bestellung bestellung) throws Exception {
		try {
			return getBestellungDAO().save(bestellung);
        } catch (Exception e) {
            String msg = "Bestellung konnte nicht durchgeführt werden";
            logger.error(msg, e);
            throw new Exception(msg);
		}
	}
	
	
	

	@Override
	public Bestellung bestellungAktualisieren(Bestellung bestellung) throws Exception {
		try {
			return getBestellungDAO().update(bestellung);
		} catch (Exception e) {
			String msg = "Bestellung konnte nicht aktualisiert werden";
			logger.error(msg, e);
			throw new Exception(msg);
		}
	}


	@Override
	public List<Bestellung> alleBestellungen() throws Exception {
		try {
			return getBestellungDAO().findAll();
		}catch (Exception e) {
			String msg = "Keine Objekte gefunden";
			logger.error(msg, e);
			throw new Exception(msg);
		}
	}

	@Override
	public List<Bestellung> findByTischNr(int TischNr) throws Exception {
		try {
			return getBestellungDAO().findByTischNr(TischNr);
		}catch (Exception e) {
			String msg = "Keine Bestellungen mit der Tisch-Nr. " + TischNr + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg);
		}
	}
	
	

	@Override
	public List<Bestellung> findByZeit(LocalDate zeit) throws Exception {
		try {
			return getBestellungDAO().findByZeit(zeit);
		}catch (Exception e) {
			String msg = "Keine Bestellung mit dem Datum " + zeit + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg);
		}
	}

}
