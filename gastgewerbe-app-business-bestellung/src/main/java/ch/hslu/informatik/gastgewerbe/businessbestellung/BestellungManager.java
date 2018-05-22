package ch.hslu.informatik.gastgewerbe.businessbestellung;

import java.time.LocalDateTime;
import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.persister.BestellungPositionDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BestellungPositionDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.api.BestellungService;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BestellungDAOImpl;


public class BestellungManager implements BestellungService {
	
	private static Logger logger = LogManager.getLogger(BestellungManager.class);

    private BestellungDAO bestellungDAO;
    private BestellungPositionDAO bestellungPosDAO;
    
    public BestellungDAO getBestellungDAO() {

        if (bestellungDAO == null) {
            bestellungDAO = new BestellungDAOImpl();
        }

        return bestellungDAO;
    }

    

    public BestellungPositionDAO getBestellungPosDAO(){
		if (bestellungPosDAO == null) {
			bestellungPosDAO = new BestellungPositionDAOImpl();
		}

		return bestellungPosDAO;
	}

    /* Bestellung am Tisch erfassen */
	public Bestellung bestellen(Bestellung bestellung) throws Exception {
		try {
			return getBestellungDAO().save(bestellung);
        } catch (Exception e) {
            String msg = "Bestellung konnte nicht durchgeführt werden";
            logger.error(msg, e);
            throw new Exception(msg, e);
		}
	}
	
	
	

	@Override
	public Bestellung bestellungAktualisieren(Bestellung bestellung) throws Exception {
		try {
			return getBestellungDAO().update(bestellung);
		} catch (Exception e) {
			String msg = "Bestellung konnte nicht aktualisiert werden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/* Bestellungsposition wird auf bereit gesetzt */
	public boolean bestellungPositionBereit(BestellungPosition bestellungPosition) throws Exception {
		try {
			bestellungPosition.setBestellungBereit(true);
			getBestellungPosDAO().update(bestellungPosition);
			return true;
		} catch (Exception e) {
			String msg = "Bestellung konnte nicht auf Status bereit werden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}


	@Override
	public List<Bestellung> alleBestellungen() throws Exception {
		try {
			return getBestellungDAO().findAll();
		}catch (Exception e) {
			String msg = "Keine Objekte gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	@Override
	public List<Bestellung> findByTischNr(int TischNr) throws Exception {
		try {
			return getBestellungDAO().findByTischNr(TischNr);
		}catch (Exception e) {
			String msg = "Keine Bestellungen mit der Tisch-Nr. " + TischNr + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}
	
	

	/* Liefer alle Bestellungen für das bestimmte Datum/Zeit */
	public List<Bestellung> findByZeit(LocalDateTime zeit) throws Exception {
		try {
			return getBestellungDAO().findByZeit(zeit);
		}catch (Exception e) {
			String msg = "Keine Bestellung mit dem Datum " + zeit + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/* Löscht übergebene Bestellung */
	public void deleteBestellung(Bestellung bestellung) throws Exception {
    	try{
    		getBestellungDAO().delete(bestellung);

		}catch (Exception e) {
			String msg = "Bestellung: " +bestellung.toString()+" konnte nicht gelöscht werden!";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}

	}

	/* Liefert Bestellung mit dieser ID */
	public Bestellung findById(Long id) throws Exception {

    	try{
    		return getBestellungDAO().findById(id);
		} catch (Exception e) {
			String msg = "Keine Bestellung mit ID " + id + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/* Liefert BestellungsPos für diese ID */
	public BestellungPosition bestPosFindById(Long id) throws Exception {
		try{
			return  getBestellungPosDAO().findById(id);
		}catch (Exception e) {
			String msg = "Keine BestellPos mit ID " + id + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}

	}

	/* Bestellungsposition wird auf ausgeliefert gesetzt */
	public boolean bestellungPositionAusgeliefert(BestellungPosition bestellungPosition) throws Exception {
		try {
			bestellungPosition.setBestellungAusgeliefert(true);
			getBestellungPosDAO().update(bestellungPosition);
			return true;
		} catch (Exception e) {
			String msg = "Bestellung konnte nicht auf Status ausgeliefert werden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/* Liefert alle Bestellung mit dem übergebenen Boolean für Bezahlungsstatus */
	public List<Bestellung> findByRechBezahlt(Boolean rechnungBezahlt) throws Exception {

    	try{
    		return getBestellungDAO().findByRechBezahlt(rechnungBezahlt);
		}catch (Exception e) {
			String msg = "Keine Bestellung mit RechStatus " + rechnungBezahlt + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/*Liefert alle BestellungPositionen mit dem übergebenen Boolean für Bestellungsstatus*/
	public List<BestellungPosition> bestPosFindByBereit(Boolean bestellungBereit) throws Exception {

    	try{
    		return getBestellungPosDAO().findByBereit(bestellungBereit);
		}catch (Exception e) {
			String msg = "Keine BestellPos mit BestellStatus " + bestellungBereit + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/*Liefert alle BestellungPositionen mit dem übergebenen Boolean fürAuslieferungsstatus*/
	public List<BestellungPosition> bestPosFindByAusgeliefert(Boolean bestellungAusgeliefert) throws Exception {

		try{
			return getBestellungPosDAO().findByAusgeliefert(bestellungAusgeliefert);
		}catch (Exception e) {
			String msg = "Keine BestellPos mit AuslieferungsStatus " + bestellungAusgeliefert + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/*Liefert alle Bestellungen mit dem übergebenen Boolean für Bestellungsstatus*/
	public List<Bestellung> findByBereit(Boolean bestellungBereit) throws Exception {
		try{
			return getBestellungDAO().findByBereit(bestellungBereit);
		}catch (Exception e) {
			String msg = "Keine Bestellung mit BestellStatus " + bestellungBereit + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/*Liefert alle Bestellungen mit dem übergebenen Boolean für Auslieferungsstatus*/
	public List<Bestellung> findByAusgeliefert(Boolean bestellungAusgeliefert) throws Exception {
		try{
			return getBestellungDAO().findByAusgeliefert(bestellungAusgeliefert);
		}catch (Exception e) {
			String msg = "Keine Bestellung mit AuslieferungsStatus " + bestellungAusgeliefert + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/*Liefert alle Bestellungen einer Tisch-Nr., mit dem übergebenen Rechnungsstatus*/
	public List<Bestellung> findByRechBezahltTisch(int TischNr, Boolean rechnungBezahlt) throws Exception {
		try{
			return getBestellungDAO().findByRechBezahltTisch(TischNr, rechnungBezahlt);
		}catch (Exception e) {
			String msg = "Keine Bestellung mit Tisch-Nr. " + TischNr + "und Rechnungsstatus: " +  rechnungBezahlt + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	
}
