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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BenutzerVerwaltenViewController {

	private static Logger logger = LogManager.getLogger(BenutzerVerwaltenViewController.class);
	
    @FXML
    private ComboBox<?> cmbRolle;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField vornameInput;

    @FXML
    private TextField strasseInput;

    @FXML
    private TextField plzInput;

    @FXML
    private TextField ortInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField telefonInput;

    @FXML
    private TextField benutzernameInput;

    @FXML
    private TextField kennwortInput;

    @FXML
    private Button spiechernBtn;

    @FXML
    private Button neuBtn;

    @FXML
    private Button resetBtn;

    @FXML
    private TableView<?> tblBenutzer;

    @FXML
    private TableColumn<?, ?> colNummer;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colVorname;

    @FXML
    private TableColumn<?, ?> colStrasse;

    @FXML
    private TableColumn<?, ?> colPlz;

    @FXML
    private TableColumn<?, ?> colOrt;

    @FXML
    private TableColumn<?, ?> colRolle;

    @FXML
    private Button löschenBtn;

    @FXML
    private Button zurückBtn;

    @FXML
    void loeschen(ActionEvent event) {

    }

    @FXML
    void neuenBenutzerErfassen(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {

    }

    @FXML
    void speichern(ActionEvent event) {

    }

    @FXML
    void zurück(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindowVerwaltung.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
	//		stage.initModality(Modality.APPLICATION_MODAL);
	//		stage.initStyle(StageStyle.UNDECORATED);
	//		stage.setTitle("Hauptseite");
			stage.setScene(new Scene(root1));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
    	} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
    }

}
