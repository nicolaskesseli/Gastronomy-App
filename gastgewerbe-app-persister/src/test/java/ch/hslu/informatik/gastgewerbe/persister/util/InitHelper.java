package ch.hslu.informatik.gastgewerbe.persister.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.*;
import ch.hslu.informatik.gastgewerbe.persister.*;
import ch.hslu.informatik.gastgewerbe.persister.impl.*;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;


public class InitHelper {

	public static final int INIT_SIZE_PRODUKT = 6;
	public static final int INIT_SIZE_TISCH = 6;
	public static final int INIT_SIZE_PERSON = 6;
	public static final int INIT_SIZE_CREDENTIALS = 6;
	public static final int INIT_SIZE_BENUTZER = 4;
	public static final int INIT_SIZE_BESTELLUNG = 1;
	public static final int INIT_SIZE_ABRECHNUNG = 1;
	public static final int INIT_SIZE_BESTELLUNGPOSITIONEN = 3;


	public static List<Bestellung> initBestellung() throws Exception {

		List<Bestellung> liste = new ArrayList<Bestellung>();

		BestellungDAO pBestellungDAO = new BestellungDAOImpl();
		ProduktDAO pProduktDAO = new ProduktDAOImpl();
		TischDAO pTisch = new TischDAOImpl();

        List<Tisch> tische = pTisch.findAll();

		Bestellung b = new Bestellung("Achtung Glutenintolleranz",tische.get(1));

		List<Produkt> produkte = pProduktDAO.findAll();

		b.getBestellungPositionListe().add(new BestellungPosition(produkte.get(0),4));
		b.getBestellungPositionListe().add(new BestellungPosition(produkte.get(1),3));
		b.getBestellungPositionListe().add(new BestellungPosition(produkte.get(2),10));

		liste.add(b);

		for (Bestellung bestellung : liste){
			pBestellungDAO.save(bestellung);
		}

		return liste;
	}

	public static void deleteAllBestellung() throws Exception {

		BestellungDAO pBestellungDAO = new BestellungDAOImpl();

		for (Bestellung b : pBestellungDAO.findAll()) {
			pBestellungDAO.delete(b);
		}
	}

	public static List<BestellungPosition> initBestellungPosition() throws Exception{

		List<BestellungPosition> liste = new ArrayList<BestellungPosition>();

		ProduktDAO pProduktDAO = new ProduktDAOImpl();
		BestellungPositionDAO  BestPosDAO = new BestellungPositionDAOImpl();

		List<Produkt> produkte = pProduktDAO.findAll();

		liste.add(new BestellungPosition(produkte.get(0), 5));
		liste.add(new BestellungPosition(produkte.get(1), 3));
		liste.add(new BestellungPosition(produkte.get(2), 6));

		for (BestellungPosition p : liste){
			BestPosDAO.save(p);
		}

		return liste;
	}

	public static void deleteAllBestellungPosition() throws Exception {

		BestellungPositionDAO  BestPosDAO = new BestellungPositionDAOImpl();

		for (BestellungPosition p : BestPosDAO.findAll()) {
			BestPosDAO.delete(p);
		}
	}

	public static List<Produkt> initProdukt() throws Exception {

		ProduktDAO p = new ProduktDAOImpl();

		List<Produkt> liste = new ArrayList<Produkt>();
		// Getränke
		liste.add(new Produkt("COLA","Coca Cola", "Koffeinhaltiges Getränk ungesund", 6.60, KategorieTyp.GETRANK));
		liste.add(new Produkt("RIVE", "Rivella Rot", "Mlchserumgetränk", 5.0, KategorieTyp.GETRANK));
		// Snacks
		liste.add(new Produkt("CHPS", "Zweifel Chips", "Kartoffelchips ungesund", 2.60, KategorieTyp.SNACK));
		liste.add(new Produkt("BUEN", "Bounty", "Kokosnusssnack zuckrig", 2.80, KategorieTyp.SNACK));
		// Speisen
		liste.add(new Produkt("PIZZ", "Pizza Maragritha", "Klassische Pizza", 19.60, KategorieTyp.SPEISE));
		liste.add(new Produkt("ZUER", "Geschnetzeltes nach Zürcher Art", "Wie mans halt kennt...", 25.90, KategorieTyp.SPEISE));

		for (Produkt t: liste){
			p.save(t);
		}


		return liste;
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
	
	public static List<Credentials> initCredentials() throws Exception {

		CredentialsDAO pCredentials = new CredentialsDAOImpl();
		List<Credentials> liste = new ArrayList<Credentials>();

		liste.add(new Credentials("hpechvogel", "hpechvogel_pwd"));
		liste.add(new Credentials("mweber", "mweber_pwd"));
		liste.add(new Credentials("rbucher", "rbucher_pwd"));
		liste.add(new Credentials("ukaufmann", "ukaufmann_pwd"));
		liste.add(new Credentials("Nicolas Kesseli", "MacChicken"));
		liste.add(new Credentials("alindauer", "alindauer_pwd"));

		for (Credentials c : liste) {
			pCredentials.save(c);
		}

		return liste;
	}

	public static void deleteAllCredentials() throws Exception {

		CredentialsDAO pCredentials = new CredentialsDAOImpl();
		for (Credentials c : pCredentials.findAll()) {
			pCredentials.delete(c);
		}
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
	
	public static List<Person> initPerson() throws Exception {

		PersonDAO pPerson = new PersonDAOImpl();
		List<Person> liste = new ArrayList<Person>();

		liste.add(new Person("Weber", "Marco", new Adresse("Lindenstrasse 12", 6030, "Ebikon"),
				new Kontakt("mweber@gmail.com", "041 111 11 11")));
		liste.add(new Person("Fischer", "Ana", new Adresse("Bundesstrasse 112", 6002, "Luzern"),
				new Kontakt("afischer1980@sbb.ch", "041 222 22 22")));
		liste.add(new Person("Pechvogel", "Hansli", new Adresse("Kirchgasse 4", 6274, "Eschenbach"),
				new Kontakt("hpechvogel@bluewin.ch", "041 333 33 33")));
		liste.add(new Person("Kaufmann", "Ursula", new Adresse("Rigistrasse 26", 6048, "Horw"),
				new Kontakt("ukaufmann@yahoo.com", "041 444 44 44")));
		liste.add(new Person("Portmann", "Roger", new Adresse("Unterdorfstrasse 12", 6033, "Buchrain"),
				new Kontakt("rportmann1975@sunrise.ch", "041 555 55 55")));
		liste.add(new Person("Lindauer", "Adrian", new Adresse("Gothardstrasse 64", 6484, "Wassen"),
				new Kontakt("alindauer@bluewin.ch", "041 666 66 66")));

		for (Person p : liste) {
			pPerson.save(p);
		}

		return liste;
	}

	public static void deleteAllPerson() throws Exception {
		PersonDAO pPerson = new PersonDAOImpl();
		for (Person p : pPerson.findAll()) {
			pPerson.delete(p);
		}
	}
	
	public static List<Tisch> initTisch() throws Exception {

		TischDAO pTisch = new TischDAOImpl();
		List<Tisch> liste = new ArrayList<Tisch>();

		liste.add(new Tisch(1));
		liste.add(new Tisch(2));
		liste.add(new Tisch(3));
		liste.add(new Tisch(4));
		liste.add(new Tisch(5));
		liste.add(new Tisch(6));

		for (Tisch t : liste) {
			pTisch.save(t);
		}

		return liste;
	}

	public static void deleteAllTisch() throws Exception {

		TischDAO pTisch = new TischDAOImpl();
		for (Tisch t : pTisch.findAll()) {
			pTisch.delete(t);
		}
	}

	public static List<Abrechnung> initAbrechnung() throws Exception{

		AbrechnungDAO pAbrechnung = new AbrechnungDAOImpl();
        BestellungDAO pBestellungDAO = new BestellungDAOImpl();
        BenutzerDAO pBenutzerDAO = new BenutzerDAOImpl();

        List<Bestellung> b = pBestellungDAO.findAll();
        List<Benutzer> ben = pBenutzerDAO.findAll();

		List<Abrechnung> liste = new ArrayList<Abrechnung>();

		Abrechnung abrechnung = new Abrechnung(ben.get(0), b.get(0));

		liste.add(abrechnung);

		for(Abrechnung a: liste){
            pAbrechnung.save(a);
        }

		return liste;
	}

    public static void deleteAllAbrechnung() throws Exception {

        AbrechnungDAO pRechnung = new AbrechnungDAOImpl();

        for (Abrechnung a : pRechnung.findAll()) {
            pRechnung.delete(a);
        }

    }


	
}
