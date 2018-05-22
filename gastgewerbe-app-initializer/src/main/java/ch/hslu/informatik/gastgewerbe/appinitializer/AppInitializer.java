package ch.hslu.informatik.gastgewerbe.appinitializer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import ch.hslu.informatik.gastgewerbe.model.*;
import ch.hslu.informatik.gastgewerbe.persister.*;
import ch.hslu.informatik.gastgewerbe.persister.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderSchemaFactory;

import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;

public class AppInitializer {
	
	private static Logger logger = LogManager.getLogger(AppInitializer.class);

	public static void main(String[] args) {

		try {
			dropTables();
			logger.info(">> DATENBANK SCHEMA NEU ERSTELLT");

			initTestdata();

			logger.info(">> DATENBANK ERFOLGREICH INITIALISIERT");

		} catch (Exception e) {
			logger.error("Initialisierung misslungen", e);
			throw new RuntimeException(e);
		}
	}

	private static void dropTables() {

		/*
		 * Es wird nur die Verbindung aufgebaut, damit die bestehenden Tabellen gelöscht und neu angelegt werden.
		 */
		JPAUtil.createEntityManagerForDelition().close();

	}

	private static void initTestdata() {
		try {
			initBenutzer();
			initProdukt();
			initTisch();
			initBestellung();
			initAbrechnung();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private static void initBenutzer() throws Exception {

		String xmlDateiName = "/benutzerInit.xml";
		String schemaDateiName = "/benutzerInitSchema.xsd";

		List<Benutzer> benutzerListe = new ArrayList<Benutzer>();

		try {

			/* Laden und Validieren der XML-Datei */

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			URL xsdURL = AppInitializer.class.getResource(schemaDateiName).toURI().toURL();
			Schema schema = schemaFactory.newSchema(xsdURL);

			XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);

			SAXBuilder sb = new SAXBuilder(factory);

			URL xmlURL = AppInitializer.class.getResource(xmlDateiName).toURI().toURL();
			Document doc = sb.build(xmlURL);

			Element users = doc.getRootElement();

			List<Element> benutzerElementliste = users.getChildren("benutzer");

			int index = 0;

			logger.info(">> Erzeugung von Benutzer gestartet.");

			for (Element element : benutzerElementliste) {

				element = benutzerElementliste.get(index++);

				int plz = Integer.parseInt(element.getChildText("plz"));

				RolleTyp rolleTyp = null;

				if (element.getChildText("rolle").equals("Administrator")) {
					rolleTyp = RolleTyp.ADMINISTRATOR;
				} else if (element.getChildText("rolle").equals("Bar-Mitarbeiter")) {
					rolleTyp = RolleTyp.BAR_MITARBEITER;
				} else if (element.getChildText("rolle").equals("Kellner")) {
					rolleTyp = RolleTyp.KELLNER;
				} else if (element.getChildText("rolle").equals("Kueche-Mitarbeiter")) {
					rolleTyp = RolleTyp.KUECHE_MITARBEITER;
				} else {
					logger.error("Fehler: RolleTyp nicht gefunden");
					throw new RuntimeException();
				}

				Benutzer benutzer = new Benutzer(element.getChildText("nachname"), element.getChildText("vorname"),
						new Adresse(element.getChildText("strasse"), plz, element.getChildText("ort")),
						new Kontakt(element.getChildText("email"), element.getChildText("telefon")),
						new Credentials(element.getChildText("benutzername"), element.getChildText("passwort")),
						rolleTyp);
				benutzerListe.add(benutzer);

				logger.info("  >> Benutzer " + benutzer.getVorname() + " " + benutzer.getNachname() + " erzeugt.");

			}

			logger.info(">> Erzeugung von Benutzer beendet.");

			BenutzerDAO dao = new BenutzerDAOImpl();

			for (Benutzer b : benutzerListe) {
				dao.save(b);
				logger.info(">> Benutzer mit Id-Nr. " + b.getId() + " wurde in die Datebank gespeichert.");
			}

		} catch (JDOMException e) {
			logger.error("Fehler bei der Validierung von " + xmlDateiName, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error("Fehler beim Lesen der XML-Datei: " + xmlDateiName + ".", e);
			throw new RuntimeException(e);
		}
	}

	private static void initProdukt() throws Exception {

		String xmlDateiName = "/produktInit.xml";
		String schemaDateiName = "/produktInitSchema.xsd";

		List<Produkt> produktListe = new ArrayList<Produkt>();

		try {

			/* Laden und Validieren der XML-Datei */

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			URL xsdURL = AppInitializer.class.getResource(schemaDateiName).toURI().toURL();
			Schema schema = schemaFactory.newSchema(xsdURL);

			XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);

			SAXBuilder sb = new SAXBuilder(factory);

			URL xmlURL = AppInitializer.class.getResource(xmlDateiName).toURI().toURL();
			Document doc = sb.build(xmlURL);

			Element users = doc.getRootElement();

			List<Element> produktElementliste = users.getChildren("produkt");

			int index = 0;

			logger.info(">> Erzeugung von Produkt gestartet.");

			for (Element element : produktElementliste) {

				element = produktElementliste.get(index++);

				double preis = Double.parseDouble(element.getChildText("preis"));

				KategorieTyp kategorieTyp = null;

				if (element.getChildText("kategorie").equals("Snack")) {
					kategorieTyp = KategorieTyp.SNACK;
				} else if (element.getChildText("kategorie").equals("Speise")) {
					kategorieTyp = KategorieTyp.SPEISE;
				} else if (element.getChildText("kategorie").equals("Getraenk")) {
					kategorieTyp = KategorieTyp.GETRANK;
				} else {
					logger.error("Fehler: KategorieTyp nicht gefunden");
					throw new RuntimeException();
				}

				Produkt produkt = new Produkt(element.getChildText("produktcode"), element.getChildText("name"),
						element.getChildText("beschreibung"), preis, kategorieTyp);
				produktListe.add(produkt);

				logger.info("  >> Produkt " + produkt.getProduktCode() + " " + produkt.getName() + " erzeugt.");

			}

			logger.info(">> Erzeugung von Produkt beendet.");

			ProduktDAO dao = new ProduktDAOImpl();

			for (Produkt p : produktListe) {
				dao.save(p);
				logger.info(">> Produkt mit Id-Nr. " + p.getId() + " wurde in die Datebank gespeichert.");
			}

		} catch (JDOMException e) {
			logger.error("Fehler bei der Validierung von " + xmlDateiName, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error("Fehler beim Lesen der XML-Datei: " + xmlDateiName + ".", e);
			throw new RuntimeException(e);
		}
	}

    private static void initTisch() throws Exception {
        logger.info(">> Erzeugung von Tischen gestartet.");
        Tisch tisch1 = new Tisch(1);
        Tisch tisch2 = new Tisch (2);
        Tisch tisch3 = new Tisch (3);
        Tisch tisch4 = new Tisch (4);
        List<Tisch> tische = new ArrayList<>();
        tische.add(tisch1);
        tische.add(tisch2);
        tische.add(tisch3);
        tische.add(tisch4);

        for (Tisch t:tische){
            logger.info("  >> Tisch " + t.getId()+" "+ t.getTischNr()+ " wurde erzeugt.");
        }
        logger.info(">> Erzeugung von Tischen beendet.");

        TischDAO tischDAO = new TischDAOImpl();

        for (Tisch t:tische){
            tischDAO.save(t);
            logger.info(">> Tisch mit Id-Nr. " + t.getId() + " wurde in die Datebank gespeichert.");
        }
    }

	private static void initAbrechnung() throws Exception {
		logger.info(">> Erzeugung von Abrechnung gestartet.");

		BenutzerDAO benDao = new BenutzerDAOImpl();
		BestellungDAO bestDao = new BestellungDAOImpl();

		LocalDateTime d = LocalDateTime.now().minusDays(3);

		Abrechnung ab1 = new Abrechnung(benDao.findById(1), bestDao.findById(30), LocalDateTime.now());
		Abrechnung ab2 = new Abrechnung(benDao.findById(3), bestDao.findById(34), d);
		Abrechnung ab3 = new Abrechnung(benDao.findByBenutzername("thelastsamurai"), bestDao.findById(41), LocalDateTime.now());

		ab1.setBetrag(300.23);
		ab2.setBetrag(600.31);
		ab3.setBetrag(234.23);


		List<Abrechnung> abrechnungListe = new ArrayList<>();
		abrechnungListe.add(ab1);
		abrechnungListe.add(ab2);
		abrechnungListe.add(ab3);

		for (Abrechnung ab:abrechnungListe){
			logger.info("  >> Abrechnung: " + ab.getId() + " Zeit: " + ab.getZeit().toString());
		}
		logger.info(">> Erzeugung von Abrechnungen beendet.");

		AbrechnungDAO abrechnungDAO = new AbrechnungDAOImpl();

		for (Abrechnung ab:abrechnungListe){
			abrechnungDAO.save(ab);
			logger.info(">> Abrechnung mit Id-Nr. " + ab.getId() + " wurde in die Datebank gespeichert.");
		}
	}

	private static void initBestellung() throws Exception {

		logger.info(">> Erzeugung von Bestellungen gestartet.");

		ProduktDAO dao = new ProduktDAOImpl();
		TischDAO tischDAO = new TischDAOImpl();
        BestellungDAO bestellungDAO = new BestellungDAOImpl();

		Bestellung best1 = new Bestellung("Achtung Gluten Intolleranz", tischDAO.findByTischNr(2), LocalDateTime.now());

		best1.getBestellungPositionListe().add(new BestellungPosition(dao.findByProduktCode("1000"), 4));
		best1.getBestellungPositionListe().add(new BestellungPosition(dao.findByProduktCode("2000"),3));
		best1.getBestellungPositionListe().add(new BestellungPosition(dao.findByProduktCode("3000"), 5));

		logger.info("  >> Bestellung " +best1.getId()+" "+ best1.getTisch() + " " + best1.getZeit() + " Anzahl Pos:" + best1.getBestellungPositionListe().size()+" erzeugt.");

		Bestellung best2 = new Bestellung("Muss extrem schnell gehen Küche!!!", tischDAO.findByTischNr(3), LocalDateTime.now().minusDays(3));

		best2.getBestellungPositionListe().add(new BestellungPosition(dao.findByProduktCode("1001"), 2));
		best2.getBestellungPositionListe().add(new BestellungPosition(dao.findByProduktCode("1002"),3));
        best2.getBestellungPositionListe().add(new BestellungPosition(dao.findByProduktCode("2001"), 3));
        best2.getBestellungPositionListe().add(new BestellungPosition(dao.findByProduktCode("2002"), 2));
        best2.getBestellungPositionListe().add(new BestellungPosition(dao.findByProduktCode("3002"), 5));
        best2.getBestellungPositionListe().add(new BestellungPosition(dao.findByProduktCode("3003"), 8));

		logger.info("  >> Bestellung " +best2.getId()+" "+ best2.getTisch() + " " + best2.getZeit() + " Anzahl Pos:" + best2.getBestellungPositionListe().size()+" erzeugt.");

		Bestellung best3 = new Bestellung("Für Abrechnungzwecke...", tischDAO.findByTischNr(4), LocalDateTime.now());

		best3.getBestellungPositionListe().add(new BestellungPosition(dao.findByProduktCode("3003"), 10));

		best3.setRechnungBezahlt(true);

		logger.info("  >> Bestellung " +best3.getId()+" "+ best3.getTisch() + " " + best3.getZeit() + " Anzahl Pos:" + best3.getBestellungPositionListe().size()+" erzeugt.");

		logger.info(">> Erzeugung von Bestellungen beendet.");

		List<Bestellung> bestellungen = new ArrayList<>();
        bestellungen.add(best1);
        bestellungen.add(best2);
        bestellungen.add(best3);

		for (Bestellung b : bestellungen) {
            bestellungDAO.save(b);
			logger.info(">> Bestellung mit Id-Nr. " + b.getId() + " wurde in die Datebank gespeichert.");
		}
	}

}
