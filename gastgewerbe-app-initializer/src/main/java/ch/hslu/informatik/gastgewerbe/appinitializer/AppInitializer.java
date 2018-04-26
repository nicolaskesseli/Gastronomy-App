package ch.hslu.informatik.gastgewerbe.appinitializer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;

import ch.hslu.informatik.gastgewerbe.model.Adresse;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.Credentials;
import ch.hslu.informatik.gastgewerbe.model.Kontakt;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.model.RolleTyp;
import ch.hslu.informatik.gastgewerbe.persister.BenutzerDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BenutzerDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.util.JPAUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderSchemaFactory;


public class AppInitializer {
	
	private static Logger logger = LogManager.getLogger(AppInitializer.class);

	public static void main(String[] args) {

		try {

			dropTables();
			logger.info(">> DATENBANK SCHEMA NEU ERSTELLT");

			initApplication();
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

	private static void initApplication() throws Exception {

		Benutzer ersterAdmin = null;
		Benutzer ersterBarMitarbeiter = null;
		Benutzer ersterKuecheMitarbeiter = null;
		Benutzer ersterKellner = null;
		Produkt erstesProdukt = null;
		Bestellung ersteBestellung = null;
	
		try {
			Properties props = new Properties();
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("moebelhaus.properties"));

			/* erster Administrator */
			String benutzerName = props.getProperty("erster_admin_name");
			String benutzerVorname = props.getProperty("erster_admin_vorname");
			String benutzerStrasse = props.getProperty("erster_admin_adresse_strasse");
			String benutzerStrPlz = props.getProperty("erster_admin_adresse_plz");
			String benutzerOrt = props.getProperty("erster_admin_adresse_ort");
			String benutzerEmail = props.getProperty("erster_admin_kontakt_mail");
			String benutzerTelefon = props.getProperty("erster_admin_kontakt_telefon");
			String benutzerBenutzername = props.getProperty("erster_admin_credentials_benutzername");
			String benutzerKennwort = props.getProperty("erster_admin_credentials_kennwort");

			int benutzerPlz = Integer.parseInt(benutzerStrPlz);

			ersterAdmin = new Benutzer(benutzerName, benutzerVorname,
					new Adresse(benutzerStrasse, benutzerPlz, benutzerOrt), new Kontakt(benutzerEmail, benutzerTelefon),
					new Credentials(benutzerBenutzername, benutzerKennwort), RolleTyp.ADMINISTRATOR);

			/* erster Barmitarbeiter */
			benutzerName = props.getProperty("erster_kasse_mitarbeiter_name");
			benutzerVorname = props.getProperty("erster_kasse_mitarbeiter_vorname");
			benutzerStrasse = props.getProperty("erster_kasse_mitarbeiter_adresse_strasse");
			benutzerStrPlz = props.getProperty("erster_kasse_mitarbeiter_adresse_plz");
			benutzerOrt = props.getProperty("erster_kasse_mitarbeiter_adresse_ort");
			benutzerEmail = props.getProperty("erster_kasse_mitarbeiter_kontakt_mail");
			benutzerTelefon = props.getProperty("erster_kasse_mitarbeiter_kontakt_telefon");
			benutzerBenutzername = props.getProperty("erster_kasse_mitarbeiter_credentials_benutzername");
			benutzerKennwort = props.getProperty("erster_kasse_mitarbeiter_credentials_kennwort");

			benutzerPlz = Integer.parseInt(benutzerStrPlz);

			ersterBarMitarbeiter = new Benutzer(benutzerName, benutzerVorname,
					new Adresse(benutzerStrasse, benutzerPlz, benutzerOrt), new Kontakt(benutzerEmail, benutzerTelefon),
					new Credentials(benutzerBenutzername, benutzerKennwort), RolleTyp.BAR_MITARBEITER);
			
			/* erster Kellner */
			benutzerName = props.getProperty("erster_kasse_mitarbeiter_name");
			benutzerVorname = props.getProperty("erster_kasse_mitarbeiter_vorname");
			benutzerStrasse = props.getProperty("erster_kasse_mitarbeiter_adresse_strasse");
			benutzerStrPlz = props.getProperty("erster_kasse_mitarbeiter_adresse_plz");
			benutzerOrt = props.getProperty("erster_kasse_mitarbeiter_adresse_ort");
			benutzerEmail = props.getProperty("erster_kasse_mitarbeiter_kontakt_mail");
			benutzerTelefon = props.getProperty("erster_kasse_mitarbeiter_kontakt_telefon");
			benutzerBenutzername = props.getProperty("erster_kasse_mitarbeiter_credentials_benutzername");
			benutzerKennwort = props.getProperty("erster_kasse_mitarbeiter_credentials_kennwort");

			benutzerPlz = Integer.parseInt(benutzerStrPlz);

			ersterKellner = new Benutzer(benutzerName, benutzerVorname,
					new Adresse(benutzerStrasse, benutzerPlz, benutzerOrt), new Kontakt(benutzerEmail, benutzerTelefon),
					new Credentials(benutzerBenutzername, benutzerKennwort), RolleTyp.KELLNER);
			
			/* erster KuecheMitarbeiter */
			benutzerName = props.getProperty("erster_kasse_mitarbeiter_name");
			benutzerVorname = props.getProperty("erster_kasse_mitarbeiter_vorname");
			benutzerStrasse = props.getProperty("erster_kasse_mitarbeiter_adresse_strasse");
			benutzerStrPlz = props.getProperty("erster_kasse_mitarbeiter_adresse_plz");
			benutzerOrt = props.getProperty("erster_kasse_mitarbeiter_adresse_ort");
			benutzerEmail = props.getProperty("erster_kasse_mitarbeiter_kontakt_mail");
			benutzerTelefon = props.getProperty("erster_kasse_mitarbeiter_kontakt_telefon");
			benutzerBenutzername = props.getProperty("erster_kasse_mitarbeiter_credentials_benutzername");
			benutzerKennwort = props.getProperty("erster_kasse_mitarbeiter_credentials_kennwort");

			benutzerPlz = Integer.parseInt(benutzerStrPlz);

			ersterKuecheMitarbeiter = new Benutzer(benutzerName, benutzerVorname,
					new Adresse(benutzerStrasse, benutzerPlz, benutzerOrt), new Kontakt(benutzerEmail, benutzerTelefon),
					new Credentials(benutzerBenutzername, benutzerKennwort), RolleTyp.KUECHE_MITARBEITER);

			/* Ersten Admin speichern */
			ersterAdmin = new BenutzerDAOImpl().save(ersterAdmin);
			/* Ersten Bar-Mitarbeiter speichern */
			ersterBarMitarbeiter = new BenutzerDAOImpl().save(ersterBarMitarbeiter);
			/* Ersten Kueche-Mitarbeiter speichern */
			ersterKuecheMitarbeiter = new BenutzerDAOImpl().save(ersterKuecheMitarbeiter);
			/* Ersten Kueche-Mitarbeiter speichern */
			ersterKuecheMitarbeiter = new BenutzerDAOImpl().save(ersterKuecheMitarbeiter);

		} catch (NumberFormatException e) {
			logger.error("Der Wert für Postleitzahl [" +"xxxx" + "] ist nicht korrekt: ", e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error("Fehler beim Einlesen der property-Datei: ", e);
			throw new RuntimeException(e);
		}
	}

	private static void initTestdata() {
		try {
			initBenutzer();
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
				} else if (element.getChildText("rolle").equals("Kueche-Mitarbeiter")) {
					rolleTyp = RolleTyp.KUECHE_MITARBEITER;
				} else if (element.getChildText("rolle").equals("Kellner")) {
					rolleTyp = RolleTyp.KELLNER;
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

}
