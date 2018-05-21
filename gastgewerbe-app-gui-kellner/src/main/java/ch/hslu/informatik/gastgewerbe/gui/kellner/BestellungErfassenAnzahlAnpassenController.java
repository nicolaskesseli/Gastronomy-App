package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungPositionWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BestellungErfassenAnzahlAnpassenController implements Initializable {

	private static Logger logger = LogManager.getLogger(BestellungErfassenController.class);

	private BestellungPositionWrapper anzahlBearbeiten;

	@FXML
	private TextField txtAnzahl;

	@FXML
	void AnzahlErhoehen(ActionEvent event) {
		String anzahl = txtAnzahl.getText();

		try {
			int iAnzahl = Integer.parseInt(anzahl);
			iAnzahl = iAnzahl + 1;

			txtAnzahl.setText(String.valueOf(iAnzahl));
		} catch (NumberFormatException e) {
			String msg = "Fehler beim Erhöhen der Anzahl";
			logger.error(msg, e);
			txtAnzahl.setText("Bitte eine ganze Zahl eingeben");
		} catch (Exception e) {
			String msg = "Fehler beim Erhöhen der Anzahl";
			logger.error(msg, e);
			txtAnzahl.setText("Bitte eine ganze Zahl eingeben");
		}

	}

	@FXML
	void AnzahlReduzieren(ActionEvent event) {
		String anzahl = txtAnzahl.getText();

		try {
			int iAnzahl = Integer.parseInt(anzahl);
			iAnzahl = iAnzahl - 1;

			txtAnzahl.setText(String.valueOf(iAnzahl));
		} catch (NumberFormatException e) {
			String msg = "Fehler beim Erhöhen der Anzahl";
			logger.error(msg, e);
			txtAnzahl.setText("Bitte eine ganze Zahl eingeben");
		} catch (Exception e) {
			String msg = "Fehler beim Erhöhen der Anzahl";
			logger.error(msg, e);
			txtAnzahl.setText("Bitte eine ganze Zahl eingeben");
		}
	}

	@FXML
	void bestaetigen(ActionEvent event)  {

		int anzahl = Integer.parseInt(txtAnzahl.getText());
		anzahlBearbeiten = Context.getInstance().getAnzahlBearbeiten();
		anzahlBearbeiten.setAnzahl(anzahl);

		Context.getInstance().getBestellungErfassenAnzahlStage().close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			txtAnzahl.setText(String.valueOf(anzahlBearbeiten.getAnzahl()));

		} catch (Exception e) {
			String msg = "Ein Fehler ist beim initialisieren aufgetreten";
			logger.error(msg, e);
			

		}

	}
}
