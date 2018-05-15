package ch.hslu.informatik.gastgewerbe.appinitializer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import ch.hslu.informatik.gastgewerbe.model.*;
import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BestellungDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderSchemaFactory;

import ch.hslu.informatik.gastgewerbe.persister.BenutzerDAO;
import ch.hslu.informatik.gastgewerbe.persister.ProduktDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BenutzerDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.impl.ProduktDAOImpl;
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

	private static void initBestellung() throws Exception {

		logger.info(">> Erzeugung von Bestellungen gestartet.");

		ProduktDAO dao = new ProduktDAOImpl();

		Bestellung best1 = new Bestellung("Achtung Gluten Intolleranz", new Tisch (12));

		BestellungPosition pos = new BestellungPosition(dao.findByProduktCode("1000"), 4);
		BestellungPosition pos2 = new BestellungPosition(dao.findByProduktCode("2000"),3);
		BestellungPosition pos3 = new BestellungPosition(dao.findByProduktCode("3000"), 5);

		List<BestellungPosition> positionen = new ArrayList<>();

		positionen.add(pos);
		positionen.add(pos2);
		positionen.add(pos3);

		best1.setBestellungPositionListe(positionen);

		logger.info("  >> Bestellung " +best1.getId()+" "+ best1.getTisch() + " " + best1.getZeit() + " Anzahl Pos:" + best1.getBestellungPositionListe().size()+" erzeugt.");

		Bestellung best2 = new Bestellung("Muss extrem schnell gehen Küche!!!", new Tisch(10));

		BestellungPosition pos4 = new BestellungPosition(dao.findByProduktCode("1001"), 2);
		BestellungPosition pos5 = new BestellungPosition(dao.findByProduktCode("1002"),3);
		BestellungPosition pos6 = new BestellungPosition(dao.findByProduktCode("2001"), 3);
		BestellungPosition pos7 = new BestellungPosition(dao.findByProduktCode("2002"), 2);
		BestellungPosition pos8 = new BestellungPosition(dao.findByProduktCode("3002"), 5);
		BestellungPosition pos9 = new BestellungPosition(dao.findByProduktCode("3003"), 8);

		List<BestellungPosition> positionen2 = new ArrayList<>();

		positionen2.add(pos4);
		positionen2.add(pos5);
		positionen2.add(pos6);
		positionen2.add(pos7);
		positionen2.add(pos8);
		positionen2.add(pos9);

		best2.setBestellungPositionListe(positionen2);

		logger.info("  >> Bestellung " +best2.getId()+" "+ best2.getTisch() + " " + best2.getZeit() + " Anzahl Pos:" + best2.getBestellungPositionListe().size()+" erzeugt.");
		logger.info(">> Erzeugung von Bestellungen beendet.");

		List<Bestellung> bestell = new ArrayList<>();
		bestell.add(best1);
		bestell.add(best2);

		BestellungDAO bestellungDAO = new BestellungDAOImpl();

		for (Bestellung b : bestell) {
			bestellungDAO.save(b);
			logger.info(">> Bestellung mit Id-Nr. " + b.getId() + " wurde in die Datebank gespeichert.");
		}
	}

}
