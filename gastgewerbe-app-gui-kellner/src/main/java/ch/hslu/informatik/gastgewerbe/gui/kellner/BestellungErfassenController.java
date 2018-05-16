	package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.ProduktWrapper;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.persister.impl.ProduktDAOImpl;

	public class BestellungErfassenController implements Initializable {

		private static Logger logger = LogManager.getLogger(BestellungErfassenController.class);
		
		private List<ProduktWrapper> produktListe = new ArrayList<>();
		
		
		
	    @FXML
	    private TextField tischNrInput;

	    @FXML
	    private TableView<ProduktWrapper> bestellübersichtTbl;

	    @FXML
	    private TableColumn<ProduktWrapper, String> code;

	    @FXML
	    private TableColumn<ProduktWrapper, String> bezeichnung2;

	    @FXML
	    private TableColumn<ProduktWrapper, String> bemerkung;

	    @FXML
	    private Button bestellungAbschickenBtn;

	    @FXML
	    private TextField bemerkungInput;

	    @FXML
	    private Button zurückInput;

	    @FXML
	    private Button gerichtHinzufügenBtn;

	    @FXML
	    private TextField gerichtNrInput;

	    @FXML
	    private Button suchenGerichtBtn;

	    @FXML
	    private TextField gerichtNameInput;

	    @FXML
	    private Button suchenGerichtBtn1;

	    @FXML
	    private TableView<ProduktWrapper> tblGerichtAuswahl;

	    @FXML
	    private TableColumn<ProduktWrapper, Double> colPreis;
	    
	    @FXML
	    private TableColumn<ProduktWrapper, String> colName;
	    
	    private ProduktWrapper gefundesProdukt;
	    
	    Table t = new Table("test", 50);
	    
	    ObservableList<Table> datenModell = FXCollections.observableArrayList();
//			
	    
	    
	    
	    @FXML
	    void bestellungAbschicken(ActionEvent event) {
	    	Bestellung bestellung = new Bestellung();
	    	
	    	try{
	    		
	    	Context.getInstance().getBestellungService().bestellen(bestellung);	
	    	
	    	}catch(Exception e) {
	    		e.getMessage();
	    		System.out.println("Ein Fehler ist aufgetreten");
	    	}
	    	
	    	
	    }

	    @FXML
	    void bestellungHinzufügen(ActionEvent event) {
	    	
	    }

	    @FXML
	    void gerichtSuchen(ActionEvent event) {
	    	
	    	String gerichtCode = gerichtNrInput.getText();
	    	Produkt p;
	    	
	    	
	    	
	    	try{
	    		
	    		p = Context.getInstance().getProduktService().findByProduktCode(gerichtCode);
	    		
	    		ProduktWrapper pWrapper = new ProduktWrapper(p);
	    		gefundesProdukt = pWrapper;
	    		
	    		updateTable();
	    	
	    	}catch(Exception e) {
	    		e.getMessage();
	    		System.out.println("Ein Fehler ist aufgetreten");
	    	}
	    }
	    
	    @FXML
	    void gerichtSuchenName(ActionEvent event) {

	    	String gerichtName = gerichtNrInput.getText();
	    	List<Produkt> pListe = new ArrayList<>();
	    	
	    	try{
	    		
	    		pListe = Context.getInstance().getProduktService().findProduktByName(gerichtName);
	     		
	    
	    	}catch(Exception e) {
	    		e.getMessage();
	    		System.out.println("Ein Fehler ist aufgetreten");
	    	}
	    }


	    @FXML
	    void zuruck(ActionEvent event) {
	    	try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
			//	stage.initModality(Modality.APPLICATION_MODAL);
			//	stage.initStyle(StageStyle.UNDECORATED);
			//	stage.setTitle("Hauptseite");
				stage.setScene(new Scene(root1));
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
	    	} catch (IOException e) {
				logger.error(e.getMessage(), e);

			}
	    }
	    
	    public void initialize(URL location, ResourceBundle resources) {
	    	
	    	try {
	    		colPreis.setCellValueFactory(new PropertyValueFactory<ProduktWrapper, Double>("preis"));
	    		colName.setCellValueFactory(new PropertyValueFactory<ProduktWrapper, String>("name"));
	    		updateTable();
	    		
	    	} catch (Exception e) {
	    		logger.error("Fehler beim initialisieren: ", e);
	    		throw new RuntimeException(e);
	    	}
	    	
	    	
		}
	    public void updateTable() {
	   
	    	
	    	try {
	    		    		
	    		tblGerichtAuswahl.getItems().clear();
		    	produktListe.clear();
	    		
	    		tblGerichtAuswahl.getItems().addAll(gefundesProdukt);
	    		tblGerichtAuswahl.getSelectionModel().select(0);
	    		
	    		
	    		
	    	} catch (Exception e) {
	    		logger.error("Fehler beim updaten der Tabelle: ", e);
	    		throw new RuntimeException(e);
	    	}
	    	
	    }
	}

	

