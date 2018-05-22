package ch.hslu.informatik.gastgewerbe.gui.verwaltung;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class VerwaltungHomeViewController implements Initializable {

	private static Logger logger = LogManager.getLogger(VerwaltungHomeViewController.class);

	private static final String GASTGEWERBE_PROPERTIES_FILE_NAME = "gastgewerbe.properties";

	@FXML
	private Label lblWillkommen;

	@FXML
	private Label lblGastgewerbe;

	@FXML
	private Label lblAdresse;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		/* Properties auslesen */

		try {

			Properties props = new Properties();

			InputStream is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(GASTGEWERBE_PROPERTIES_FILE_NAME);

			if (is != null) {
				props.load(is);
			} else {
				throw new RuntimeException(
						"Property File \'" + GASTGEWERBE_PROPERTIES_FILE_NAME + "\' nicht gefunden!");
			}

			String strWillkommen = props.getProperty("willkommen_meldung", "Willkommen");
			String strName = props.getProperty("gastgewerbe_name", " ");
			String strStrasse = props.getProperty("gastgewerbe_strasse", " ");
			String strPlz = props.getProperty("gastgewerbe_plz", " ");
			String strOrt = props.getProperty("gastgewerbe_ort", " ");

			lblWillkommen.setText(strWillkommen);
			lblGastgewerbe.setText(strName);
			lblAdresse.setText(strPlz + " - " + strOrt + ", " + strStrasse);

		} catch (IOException e) {
			String msg = "Fehler beim Holen des 'gastgewerbe.properties' Datei:";
			logger.error(msg, e);
			throw new RuntimeException(msg);
		}

	}

}
