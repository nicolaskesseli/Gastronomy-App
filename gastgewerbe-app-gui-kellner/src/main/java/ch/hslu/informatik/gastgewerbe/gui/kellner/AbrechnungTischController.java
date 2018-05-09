package ch.hslu.informatik.gastgewerbe.gui.kellner;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AbrechnungTischController {

	private static Logger logger = LogManager.getLogger(BestellungBereitController.class);
	
    @FXML
    private Label tischNr;

    @FXML
    private TextField tischNrInput;

    @FXML
    private Button offeneBestellungSuchenBtn;

    @FXML
    private TableView<?> übersichtBestellungenTabelle;

    @FXML
    private TextArea TotalCHFOutput;

    @FXML
    private Button quittungDruckenBtn;

    @FXML
    private Button getrennteBezahlenBtn;

    @FXML
    private Button kartenZahlungBtn;

    @FXML
    private Button bestellungAbschliessenBtn;

    @FXML
    private Button zurückBtn;

    @FXML
    void bestellungAbschliessen(ActionEvent event) {

    }

    @FXML
    void getrennteBezahlung(ActionEvent event) {

    }

    @FXML
    void kartenZahlung(ActionEvent event) {

    }

    @FXML
    void offeneBestellungSuchen(ActionEvent event) {

    }

    @FXML
    void quittungDrucken(ActionEvent event) {

    }

    @FXML
    void zurück(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Hauptseite");
			stage.setScene(new Scene(root1));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
    	} catch (IOException e) {
			logger.error(e.getMessage(), e);
    	}
    }

}
