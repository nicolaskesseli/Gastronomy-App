package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungPositionWrapper;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungWrapper;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.ProduktWrapper;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.model.Tisch;
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

	
	private List<Bestellung> offeneBestellungen = new ArrayList<>();
	
	private List<BestellungPosition> offeneBestellungPosition = new ArrayList<>();
	
	private List<BestellungWrapper> bestellungenListeWrapper = new ArrayList<>();

	private List<BestellungPositionWrapper> offeneBestellungPositionWrapperListe = new ArrayList<>();
	
	private int tischNr;
	
	private List<BestellungPosition> pListe = new ArrayList<>();

	private double gesamtBetrag;
	
	private Benutzer benutzer;
	
	private Tisch tisch;

	@FXML
	private TextField tischNrInput;

	@FXML
	private Button offeneBestellungSuchenBtn;

	@FXML
	private TableView<BestellungPositionWrapper> tblUebersichtBestellung;

	@FXML
	private TableColumn<BestellungPositionWrapper, String> colNummer;

	@FXML
	private TableColumn<BestellungPositionWrapper, String> colName;

	@FXML
	private TableColumn<BestellungPositionWrapper, Double> colPreis;

	@FXML
	private TableColumn<BestellungPositionWrapper, Integer> colAnzahl;

	@FXML
	private Label lblTotal;

	@FXML
	private Button bestellungAbschliessenBtn;

	@FXML
	private Button zurückBtn;


	@FXML
	void bestellungAbschliessen(ActionEvent event) throws Exception {

		
		try {
			
		benutzer = Context.getInstance().getBenutzer();
			
		tisch = Context.getInstance().getTischService().findByTischNummer(Integer.parseInt(tischNrInput.getText()));

		List<Bestellung> abzurechnendeBest = Context.getInstance().getBestellungService().findByRechBezahltTisch(tisch.getTischNr(), false);

		// logger info für debugging
		logger.info(abzurechnendeBest.size() + abzurechnendeBest.get(0).toString());

		if( abzurechnendeBest.size()!= 0 && abzurechnendeBest.size()<2){
			gesamtBetrag = Context.getInstance().getAbrechnungService().tischAbrechnen(tisch, benutzer, abzurechnendeBest.get(0));
		}

		tblUebersichtBestellung.getItems().clear();
		tischNrInput.setText("");
		
		} catch (NumberFormatException e) {
			String msg = "Keine Nummer im Eingabefeld";
			String ausgabe = "Nummer eingeben";
			tischNrInput.setText(ausgabe);
			throw new Exception(msg, e);
		} catch (Exception e) {
			String msg = "Ein Fehler ist bei der Bestellsuche aufgetreten";
			logger.error(msg, e);
			throw new Exception(msg, e);
			
		}
		
		
		
	}

	@FXML
	void offeneBestellungSuchen(ActionEvent event) throws Exception {

		try {
			tischNr = Integer.parseInt(tischNrInput.getText());
			offeneBestellungen = Context.getInstance().getBestellungService().findByRechBezahltTisch(tischNr, false);
			
			updateTable();
		
			
		} catch (NumberFormatException e) {
			String msg = "Keine Nummer im Eingabefeld";
			String ausgabe = "Nummer eingeben";
			tischNrInput.setText(ausgabe);
			throw new Exception(msg, e);
		} catch (Exception e) {
			String msg = "Ein Fehler ist bei der Bestellsuche aufgetreten";
			logger.error(msg, e);
			throw new Exception(msg, e);
		}

	}
	
	

	@FXML
	void zurück(ActionEvent event) {
		try {
			AnchorPane backRoot = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

			Scene backScene = new Scene(backRoot);

			Stage mainStage = Context.getInstance().getMainStage();

			mainStage.setScene(backScene);
			mainStage.show();

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		colPreis.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Double>("preis"));
		colName.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, String>("name"));
		colAnzahl.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Integer>("anzahl"));
		colNummer.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, String>("produktCode"));

	}

	
	@FXML
	public void updateTable() {

		try {

			tblUebersichtBestellung.getItems().clear();
			offeneBestellungPositionWrapperListe.clear();
			
			
			int i = 0;

			for (Bestellung b : offeneBestellungen) {
				for(i=0; i<b.getBestellungPositionListe().size();i++) {
					
					pListe.add(b.getBestellungPositionListe().get(i));
					
				}
			}
			
			for(BestellungPosition bestellungPosition : pListe) {
				offeneBestellungPositionWrapperListe.add(new BestellungPositionWrapper(bestellungPosition));
			}
			
			
			tblUebersichtBestellung.getItems().addAll(offeneBestellungPositionWrapperListe);
			tblUebersichtBestellung.getSelectionModel().select(0);
			
			
			for (BestellungPosition a : pListe){
	            gesamtBetrag += a.getProdukt().getPreis()*a.getAnzahl();
	        }
			
			String gesamtTotal = String.valueOf(gesamtBetrag);
			
			lblTotal.setText(gesamtTotal + " CHF");

			pListe.clear();
			gesamtBetrag = 0;
			gesamtTotal = "";
			
		

		} catch (Exception e) {
			logger.error("Fehler beim updaten der Tabelle: ", e);
			throw new RuntimeException(e);
		}

	}

}
