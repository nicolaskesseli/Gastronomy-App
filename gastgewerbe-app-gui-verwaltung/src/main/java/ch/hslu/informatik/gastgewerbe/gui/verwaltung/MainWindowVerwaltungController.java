package ch.hslu.informatik.gastgewerbe.gui.verwaltung;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindowVerwaltungController {

	private static Logger logger = LogManager.getLogger(MainWindowVerwaltungController.class);
	
    @FXML
    private Button benutzerVerwaltenBtn;

    @FXML
    private Button produkteVerwalten;
    
    @FXML
    private Button bestellungVerwalten;

    @FXML
    private Button abmeldeBtn;

    @FXML
    void abmelden(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			//stage.initModality(Modality.APPLICATION_MODAL);
		//	stage.initStyle(StageStyle.UNDECORATED);
		//	stage.setTitle("Login");
			stage.setScene(new Scene(root1));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
    }

    @FXML
    void benutzerVerwalten(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BenutzerVerwaltenView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
		//	stage.initModality(Modality.APPLICATION_MODAL);
		//	stage.initStyle(StageStyle.UNDECORATED);
		//	stage.setTitle("Benutzerverwaltung");
			stage.setScene(new Scene(root1));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
    }

    @FXML
    void produkteVerwalten(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ProduktVerwaltenView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
		//	stage.initModality(Modality.APPLICATION_MODAL);
		//	stage.initStyle(StageStyle.UNDECORATED);
		//	stage.setTitle("Produktverwaltung");
			stage.setScene(new Scene(root1));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
    }

	@FXML
	void bestellungenVerwalten(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BestellungView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			//	stage.initModality(Modality.APPLICATION_MODAL);
			//	stage.initStyle(StageStyle.UNDECORATED);
			//	stage.setTitle("Produktverwaltung");
			stage.setScene(new Scene(root1));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
	}

}
