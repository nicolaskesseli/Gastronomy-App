	package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.persister.impl.ProduktDAOImpl;

	public class BestellungErfassenController implements Initializable {

		private static Logger logger = LogManager.getLogger(BestellungErfassenController.class);
		
	    @FXML
	    private TextField tischNrInput;

	    @FXML
	    private TableView<?> bestellübersichtTbl;

	    @FXML
	    private TableColumn<Table, String> code;

	    @FXML
	    private TableColumn<Table, String> bezeichnung2;

	    @FXML
	    private TableColumn<Table, String> bemerkung;

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
	    private TableView<Table> gerichtAuswahl;

	    @FXML
	    private TableColumn<Table, Double> preis;
	    
	    @FXML
	    private TableColumn<Table, String> bezeichnung;
	    
	    
	    
	    final ObservableList<Table> data = FXCollections.observableArrayList(
//				new Table(p.getBeschreibung(), p.getPreis()),
				new Table("12.50", 100)
				);
				
				
	    
	    
	    
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
		System.out.println("Initialize aufgerufen");
	    	bezeichnung.setCellValueFactory(new PropertyValueFactory<Table, String>("rBezeichnung"));
	    	preis.setCellValueFactory(new PropertyValueFactory<Table, Double>("rPreis"));
	    	
	    	gerichtAuswahl.setItems(data);
		}

	}

	

