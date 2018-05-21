package ch.hslu.informatik.gastgewerbe.businessabrechnung;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.hslu.informatik.gastgewerbe.api.AbrechnungService;
import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.Tisch;
import ch.hslu.informatik.gastgewerbe.persister.AbrechnungDAO;
import ch.hslu.informatik.gastgewerbe.persister.BenutzerDAO;
import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import ch.hslu.informatik.gastgewerbe.persister.TischDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.AbrechnungDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.impl.BenutzerDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.impl.BestellungDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.impl.TischDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbrechnungManager implements AbrechnungService {

	private static Logger logger = LogManager.getLogger(AbrechnungManager.class);

	private AbrechnungDAO abrechnungDAO;
	private BestellungDAO bestellungDAO;
	private TischDAO tischDAO;
	
	private Abrechnung abrechnung;
	private double betrag;

	public AbrechnungDAO getAbrechnungDAO(){
		if (abrechnungDAO == null) {
			abrechnungDAO = new AbrechnungDAOImpl();
		}
		return abrechnungDAO;
	}

	public BestellungDAO getBestellungDAO(){
		if (bestellungDAO == null) {
			bestellungDAO = new BestellungDAOImpl();
		}
		return bestellungDAO;
	}

	public TischDAO getTischDAO(){
		if (tischDAO == null) {
			tischDAO = new TischDAOImpl();
		}
		return tischDAO;
	}




    @Override
    public double tischAbrechnen(Tisch tisch, Benutzer benutzer, Bestellung bestellung) throws Exception {

		try {

			Abrechnung abrechnung = new Abrechnung(benutzer, bestellung);

			// Abrechnung speichern
			getAbrechnungDAO().save(abrechnung);

			// Bestellung auf abgerechnet setzen und updaten
			bestellung.setRechnungBezahlt(true);
			getBestellungDAO().update(bestellung);

			logger.info(abrechnung.toString()+ " wrude erstellt!");

			double betrag = abrechnung.getGesamtBetrag();

			return betrag;

		} catch (Exception e){
			String msg = "Tisch Abrechnen misslungen";
			logger.error(msg, e);
			throw new Exception(msg);
		}
	}


	@Override
	public double abschluss(LocalDateTime zeit) throws Exception {
		// TODO Auto-generated method stub

		try{
			List<Abrechnung> tagesAbschluss = getAbrechnungDAO().findByDatum(zeit);

			double gesamtBetragTagesabschluss=0;

			for (Abrechnung a: tagesAbschluss){
				if(a.getBestellung().isRechnungBezahlt()==true)
				gesamtBetragTagesabschluss = a.getBetrag() + gesamtBetragTagesabschluss;
			}

			return gesamtBetragTagesabschluss;


		} catch (Exception e){
			String msg = "Tagesabrechnung misslungen";
			logger.error(msg, e);
			throw new Exception(msg);
		}
	}

	@Override
	public List<Abrechnung> findByBenutzerUndDatum(Benutzer benutzer, LocalDateTime zeit) throws Exception {
		// TODO Auto-generated method stub
		try{
			return getAbrechnungDAO().findByBenutzerUndDatum(benutzer, zeit);
		} catch (Exception e) {
			String msg = "Rechnungen des Benutzers " + benutzer.getNachname() + " " + benutzer.getVorname()
					+ " konnten nicht geholt werden";
			logger.error(msg, e);
			throw new Exception(msg);
		}
		}
	
	@Override
	public Tisch findByTischNr(int TischNr) throws Exception {
		try{
			return getTischDAO().findByTischNr(TischNr);
		}catch (Exception e) {
			String msg = "Keinen Tisch mit Tisch-Nr. " + TischNr + " gefunden";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	}

