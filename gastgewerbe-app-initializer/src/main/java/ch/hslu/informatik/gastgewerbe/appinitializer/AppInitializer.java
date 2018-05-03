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

}
