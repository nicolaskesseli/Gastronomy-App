package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {

	private static Logger logger = LogManager.getLogger(MainWindowController.class);

	@FXML
	private Button tischAbrechnenBtn;

	@FXML
	private Button bestellungErfassen;

	@FXML
	private Button offeneBestellungen;

	@FXML
	private Button abmeldeBtn;

	@FXML
	private Button bestellungBearbeitenBtn;

	@FXML
	private Label lblBenutzer;

	@FXML
	void bestellungErfassen(ActionEvent event) {
		try {
			AnchorPane bestellungErfassenRoot = FXMLLoader
					.load(getClass().getResource("/fxml/BestellungErfassenView.fxml"));
			Scene bestellungErfassenScene = new Scene(bestellungErfassenRoot);

			Stage mainStage = Context.getInstance().getMainStage();

			mainStage.setScene(bestellungErfassenScene);
			mainStage.show();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
	}

	@FXML
	void bestellungBearbeiten(ActionEvent event) {

		try {
			AnchorPane bestellungBearbeitenRoot = FXMLLoader
					.load(getClass().getResource("/fxml/BestellungBearbeiten.fxml"));
			Scene bestellungBearbeitenScene = new Scene(bestellungBearbeitenRoot);

			Stage mainStage = Context.getInstance().getMainStage();

			mainStage.setScene(bestellungBearbeitenScene);
			mainStage.show();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}

	}

	@FXML
	void offeneBestellungen(ActionEvent event) {
		try {
			AnchorPane mainRoot = FXMLLoader.load(getClass().getResource("/fxml/BestellungBereitView.fxml"));
			Scene mainScene = new Scene(mainRoot);

			Stage mainStage = Context.getInstance().getMainStage();

			mainStage.setScene(mainScene);
			mainStage.show();

		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
	}

	@FXML
	void tischAbrechnen(ActionEvent event) {
		try {
			AnchorPane mainRoot = FXMLLoader.load(getClass().getResource("/fxml/AbrechnungTischView.fxml"));
			Scene mainScene = new Scene(mainRoot);

			Stage mainStage = Context.getInstance().getMainStage();

			mainStage.setScene(mainScene);
			mainStage.show();

		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
	}

	@FXML
	private void abmelden(ActionEvent event) {

		try {
			AnchorPane abmeldenRoot = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));

			Scene abmeldenScene = new Scene(abmeldenRoot);

			Stage mainStage = Context.getInstance().getMainStage();

			mainStage.setScene(abmeldenScene);
			mainStage.show();

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);

		}

	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		String str = Context.getInstance().getBenutzer().getVorname() + " "
				+ Context.getInstance().getBenutzer().getNachname();
		lblBenutzer.setText("Angemeldet: " + str);
		lblBenutzer.setAlignment(Pos.BASELINE_CENTER);

	}

}
