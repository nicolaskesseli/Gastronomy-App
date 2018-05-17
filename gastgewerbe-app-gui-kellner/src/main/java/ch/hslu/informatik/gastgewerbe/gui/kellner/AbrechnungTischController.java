package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungPositionWrapper;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungWrapper;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.ProduktWrapper;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AbrechnungTischController implements Initializable {

	private static Logger logger = LogManager.getLogger(BestellungBereitController.class);
	
	private List<BestellungPositionWrapper> bestellungPositionListe = new ArrayList<>();
	
    @FXML
    private Label tischNr;

    @FXML
    private TextField tischNrInput;

    @FXML
    private Button offeneBestellungSuchenBtn;

    @FXML
    private TableView<?> tblUebersichtBestellung;
    
    @FXML
	private TableColumn<BestellungPositionWrapper, String> colNummer;

    @FXML
	private TableColumn<BestellungPositionWrapper, String> colName;
    
    @FXML
	private TableColumn<BestellungPositionWrapper, Double> colPreis;
    
    @FXML
	private TableColumn<BestellungPositionWrapper, Integer> colAnzahl;
    
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
    
	private ProduktWrapper gefundesProdukt;


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
			//stage.initModality(Modality.APPLICATION_MODAL);
		//	stage.initStyle(StageStyle.UNDECORATED);
		//	stage.setTitle("Hauptseite");
			stage.setScene(new Scene(root1));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
    	} catch (IOException e) {
			logger.error(e.getMessage(), e);
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colPreis.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Double>("preis"));
		colName.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, String>("name"));
		colAnzahl.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Integer>("anzahl"));
		colNummer.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, String>("produktCode"));
		
			
	}
	
	public void updateTableTischSuche() {


		
		try {
			
			tblUebersichtBestellung.getItems().clear();
			bestellungPositionListe.clear();
	
			
			//tblUebersichtBestellung.getItems().addAll(gefundesProdukt);
			tblUebersichtBestellung.getSelectionModel().select(0);

			gefundesProdukt = null;

		} catch (Exception e) {
			logger.error("Fehler beim updaten der Tabelle: ", e);
			throw new RuntimeException(e);
		}

	}

}
