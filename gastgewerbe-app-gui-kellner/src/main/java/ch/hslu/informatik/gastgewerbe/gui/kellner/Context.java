package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungPositionWrapper;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiAbrechnungService;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiBestellungService;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiLoginService;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiProduktService;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiTischService;
import javafx.stage.Stage;

public class Context {

	private static Logger logger = LogManager.getLogger(Context.class);

	private static final String PROPERTY_FILE_NAME = "rmi_client.properties";
	private static final String POLICY_FILE_NAME = "rmi_client.policy";

	private static Context INSTANCE = new Context();

	private Stage mainStage;

	private Stage bestellungErfassenAnzahlStage;

	private Benutzer benutzer;

	private RmiProduktService produktService;

	private RmiTischService tischService;

	private RmiLoginService loginService;

	private RmiBestellungService bestellungService;

	private RmiAbrechnungService abrechnungService;

	private BestellungPositionWrapper anzahlBearbeiten;

	private Context() {

	}

	public static Context getInstance() {

		return INSTANCE;
	}

	public BestellungPositionWrapper getAnzahlBearbeiten() {
		return anzahlBearbeiten;
	}

	public void setAnzahlBearbeiten(BestellungPositionWrapper anzahlBearbeiten) {
		this.anzahlBearbeiten = anzahlBearbeiten;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public Stage getBestellungErfassenAnzahlStage() {
		return bestellungErfassenAnzahlStage;
	}

	public void setBestellungErfassenAnzahlStage(Stage bestellungErfassenAnzahlStage) {
		this.bestellungErfassenAnzahlStage = bestellungErfassenAnzahlStage;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	// Liefert RmiBestellungService zurück
	public RmiBestellungService getBestellungService() {

		int portNr = 0;

		if (bestellungService == null) {

			try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

				setSecurityManager();

				Properties props = new Properties();

				if (is == null) {
					throw new RuntimeException(
							"Die Property-Datei \'" + PROPERTY_FILE_NAME + "\' konnte nicht gefunden werden!");
				} else {

					props.load(is);

					String ip = props.getProperty("rmi.server.ip");
					String strPort = props.getProperty("rmi.registry.port");

					try {
						portNr = Integer.parseInt(strPort);
						Registry reg = LocateRegistry.getRegistry(ip, portNr);

						if (reg != null) {
							String url = "rmi://" + ip + ":" + portNr + "/" + RmiBestellungService.REMOTE_OBJECT_NAME;

							bestellungService = (RmiBestellungService) Naming.lookup(url);

						} else {
							String msg = "Die Reference auf RMI-Registry konnte auf " + ip + ":" + portNr
									+ " nicht geholt werden!";
							logger.error(msg);
							throw new RuntimeException(msg);
						}

					} catch (NumberFormatException nfe) {
						String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
						logger.error(msg, nfe);
						throw new RuntimeException(nfe);
					}
				}
			} catch (Exception e) {
				String msg = "Fehler beim Holen des RmiBestellungRO:";
				logger.error(msg, e);
				throw new RuntimeException(msg);
			}
		}

		return bestellungService;
	}

	// Liefert RmiTischService zurück
	public RmiTischService getTischService() {

		int portNr = 0;

		if (tischService == null) {

			try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

				setSecurityManager();

				Properties props = new Properties();

				if (is == null) {
					throw new RuntimeException(
							"Die Property-Datei \'" + PROPERTY_FILE_NAME + "\' konnte nicht gefunden werden!");
				} else {

					props.load(is);

					String ip = props.getProperty("rmi.server.ip");
					String strPort = props.getProperty("rmi.registry.port");

					try {
						portNr = Integer.parseInt(strPort);
						Registry reg = LocateRegistry.getRegistry(ip, portNr);

						if (reg != null) {
							String url = "rmi://" + ip + ":" + portNr + "/" + RmiTischService.REMOTE_OBJECT_NAME;

							tischService = (RmiTischService) Naming.lookup(url);

						} else {
							String msg = "Die Reference auf RMI-Registry konnte auf " + ip + ":" + portNr
									+ " nicht geholt werden!";
							logger.error(msg);
							throw new RuntimeException(msg);
						}

					} catch (NumberFormatException nfe) {
						String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
						logger.error(msg, nfe);
						throw new RuntimeException(nfe);
					}
				}
			} catch (Exception e) {
				String msg = "Fehler beim Holen des RmiBestellungRO:";
				logger.error(msg, e);
				throw new RuntimeException(msg);
			}
		}

		return tischService;
	}

	// Liefert RmiLoginService zurück
	public RmiLoginService getLoginService() {

		int portNr = 0;

		if (loginService == null) {

			try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

				setSecurityManager();

				Properties props = new Properties();

				if (is == null) {
					throw new RuntimeException(
							"Die Property-Datei \'" + PROPERTY_FILE_NAME + "\' konnte nicht gefunden werden!");
				} else {

					props.load(is);

					String ip = props.getProperty("rmi.server.ip");
					String strPort = props.getProperty("rmi.registry.port");

					try {
						portNr = Integer.parseInt(strPort);
						Registry reg = LocateRegistry.getRegistry(ip, portNr);

						if (reg != null) {
							String url = "rmi://" + ip + ":" + portNr + "/" + RmiLoginService.REMOTE_OBJECT_NAME;

							loginService = (RmiLoginService) Naming.lookup(url);

						} else {
							String msg = "Die Reference auf RMI-Registry konnte auf " + ip + ":" + portNr
									+ " nicht geholt werden!";
							logger.error(msg);
							throw new RuntimeException(msg);
						}

					} catch (NumberFormatException nfe) {
						String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
						logger.error(msg, nfe);
						throw new RuntimeException(nfe);
					}
				}
			} catch (Exception e) {
				String msg = "Fehler beim Holen des RmiLoginRO:";
				logger.error(msg, e);
				throw new RuntimeException(msg);
			}
		}

		return loginService;
	}

	// Liefert RmiProduktService zurück
	public RmiProduktService getProduktService() {

		int portNr = 0;

		if (produktService == null) {

			try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

				setSecurityManager();

				Properties props = new Properties();

				if (is == null) {
					throw new RuntimeException(
							"Die Property-Datei \'" + PROPERTY_FILE_NAME + "\' konnte nicht gefunden werden!");
				} else {

					props.load(is);

					String ip = props.getProperty("rmi.server.ip");
					String strPort = props.getProperty("rmi.registry.port");

					try {
						portNr = Integer.parseInt(strPort);
						Registry reg = LocateRegistry.getRegistry(ip, portNr);

						if (reg != null) {
							String url = "rmi://" + ip + ":" + portNr + "/" + RmiProduktService.REMOTE_OBJECT_NAME;

							produktService = (RmiProduktService) Naming.lookup(url);

						} else {
							String msg = "Die Reference auf RMI-Registry konnte auf " + ip + ":" + portNr
									+ " nicht geholt werden!";
							logger.error(msg);
							throw new RuntimeException(msg);
						}

					} catch (NumberFormatException nfe) {
						String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
						logger.error(msg, nfe);
						throw new RuntimeException(nfe);
					}
				}
			} catch (Exception e) {
				String msg = "Fehler beim Holen des RmiProduktRO:";
				logger.error(msg, e);
				throw new RuntimeException(msg);
			}
		}

		return produktService;
	}

	// Liefert RmiAbrechnungService zurück
	public RmiAbrechnungService getAbrechnungService() {

		int portNr = 0;

		abrechnungService = null;

		if (abrechnungService == null) {

			try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

				setSecurityManager();

				Properties props = new Properties();

				if (is == null) {
					throw new RuntimeException(
							"Die Property-Datei \'" + PROPERTY_FILE_NAME + "\' konnte nicht gefunden werden!");
				} else {

					props.load(is);

					String ip = props.getProperty("rmi.server.ip");
					String strPort = props.getProperty("rmi.registry.port");

					try {
						portNr = Integer.parseInt(strPort);
						Registry reg = LocateRegistry.getRegistry(ip, portNr);

						if (reg != null) {
							String url = "rmi://" + ip + ":" + portNr + "/" + RmiAbrechnungService.REMOTE_OBJECT_NAME;

							abrechnungService = (RmiAbrechnungService) Naming.lookup(url);

						} else {
							String msg = "Die Reference auf RMI-Registry konnte auf " + ip + ":" + portNr
									+ " nicht geholt werden!";
							logger.error(msg);
							throw new RuntimeException(msg);
						}

					} catch (NumberFormatException nfe) {
						String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
						logger.error(msg, nfe);
						throw new RuntimeException(nfe);
					}
				}
			} catch (Exception e) {
				String msg = "Fehler beim Holen des RmiAbrechnungRO:";
				logger.error(msg, e);
				throw new RuntimeException(msg, e);
			}
		}

		return abrechnungService;
	}

	/* Diese Methode setzt den SecurityManager */
	private void setSecurityManager() throws IOException {

		/*
		 * Hinweis: die rmi-policy File ist entweder im Verzeichnis 'src' oder in
		 * 'resources', wenn man mit maven arbeitet
		 */

		InputStream is = this.getClass().getClassLoader().getResourceAsStream(POLICY_FILE_NAME);

		File tempFile = File.createTempFile(System.getProperty("user.home") + File.separator + "gastgewerbe_rmi_policy",
				"tmp");

		FileOutputStream fos = new FileOutputStream(tempFile);

		/* Inhalt der policy-Datei in 'tempFile' kopieren */
		int n = 0;

		while ((n = is.read()) != -1) {
			fos.write(n);
		}

		is.close();
		fos.close();

		String pathToTempPolicyFile = tempFile.getAbsolutePath();

		tempFile.deleteOnExit();

		if (System.getSecurityManager() == null) {
			/* Policy-File muss im ROOT-Verzeichnis des Projekts sein! */
			System.setProperty("java.security.policy", pathToTempPolicyFile);
			System.setSecurityManager(new SecurityManager());
		}
	}

}
