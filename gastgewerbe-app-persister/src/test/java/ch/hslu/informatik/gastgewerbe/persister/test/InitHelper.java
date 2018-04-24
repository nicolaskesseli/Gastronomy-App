package ch.hslu.informatik.gastgewerbe.persister.test;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.*;
import ch.hslu.informatik.gastgewerbe.persister.BenutzerDAO;
import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import ch.hslu.informatik.gastgewerbe.persister.ProduktDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BenutzerDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.impl.BestellungDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.impl.ProduktDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;


public class InitHelper {

	public static final int INIT_SIZE_PRODUKT_TYP = 9;
	public static final int INIT_SIZE_PRODUKT = 9;
	public static final int INIT_SIZE_PERSON = 6;
	public static final int INIT_SIZE_CREDENTIALS = 6;
	public static final int INIT_SIZE_BENUTZER = 4;
	public static final int INIT_SIZE_BESTELLUNG_POSITION = 3;
	public static final int INIT_SIZE_BESTELLUNG = 1;
	public static final int INIT_SIZE_LIEFERUNG_POSITION = 6;
	public static final int INIT_SIZE_LIEFERUNG = 2;
	public static final int INIT_SIZE_RECHNUNG = 2;

	public static List<Bestellung> initBestellung() throws Exception {
		return null;
	}

	public static void deleteAllBestellung() throws Exception {

		BestellungDAO pBestellungDAO = new BestellungDAOImpl();

		for (Bestellung b : pBestellungDAO.findAll()) {
			pBestellungDAO.delete(b);
		}
	}

	public static List<Produkt> initProdukt() throws Exception {
		return null;
	}



	public static void deleteAllProdukt() throws Exception {
		ProduktDAO pProdukt = new ProduktDAOImpl();
		for (Produkt p : pProdukt.findAll()) {
			pProdukt.delete(p);
		}
	}



	public static List<Person> createPersonListe() {

		List<Person> liste = new ArrayList<>();

		liste.add(new Person("Studer", "Peter", new Adresse("Kichrgasse 2", 6010, "Kriens"),
				new Kontakt("pmeier@gmx.ch", "079 999 99 99")));
		liste.add(new Person("Weber", "Beat", new Adresse("Lindenstrasse 12", 6000, "Luzern"),
				new Kontakt("bweber@gmx.ch", "079 888 88 88")));
		liste.add(new Person("Bucher", "Marco", new Adresse("Bundesstrasse 122", 6000, "Luzern"),
				new Kontakt("mbucher@gmx.ch", "079 777 77 77")));

		return liste;

	}
	
	public static List<Benutzer> initBenutzer() throws Exception {

		BenutzerDAO pBenutzer = new BenutzerDAOImpl();

		List<Benutzer> liste = new ArrayList<Benutzer>();

		liste.add(new Benutzer("Weber", "Marco", new Adresse("Lindenstrasse 12", 6030, "Ebikon"),
				new Kontakt("mweber@gmail.com", "041 111 11 11"), new Credentials("mweber", "mweber_pwd"),
				RolleTyp.KELLNER));
		liste.add(new Benutzer("Fischer", "Ana", new Adresse("Bundesstrasse 112", 6002, "Luzern"),
				new Kontakt("afischer1980@sbb.ch", "041 222 22 22"), new Credentials("afischer", "afischer_pwd"),
				RolleTyp.BAR_MITARBEITER));
		liste.add(new Benutzer("Portmann", "Roger", new Adresse("Unterdorfstrasse 12", 6033, "Buchrain"),
				new Kontakt("rportmann1975@sunrise.ch", "041 555 55 55"), new Credentials("rportmann", "rportmann_pwd"),
				RolleTyp.KELLNER));
		liste.add(new Benutzer("Lindauer", "Adrian", new Adresse("Gothardstrasse 64", 6484, "Wassen"),
				new Kontakt("alindauer@bluewin.ch", "041 666 66 66"), new Credentials("alindauer", "alindauer_pwd"),
				RolleTyp.BAR_MITARBEITER));

		for (Benutzer b : liste) {
			pBenutzer.save(b);
		}

		return liste;
	}
	
	public static void resetDb() {
		JPAUtil.createEntityManagerForDelition().close();
	}
	
	public static void deleteAllBenutzer() throws Exception {

		BenutzerDAO pBenutzer = new BenutzerDAOImpl();

		for (Benutzer b : pBenutzer.findAll()) {
			pBenutzer.delete(b);
		}

	}
}
