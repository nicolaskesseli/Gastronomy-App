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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungPositionWrapper;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungWrapper;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.ProduktWrapper;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.persister.impl.ProduktDAOImpl;

public class BestellungErfassenController implements Initializable {

	private static Logger logger = LogManager.getLogger(BestellungErfassenController.class);
	
	private BestellungPositionWrapper position;

	private List<ProduktWrapper> produktListe = new ArrayList<>();
	
	private List<BestellungPositionWrapper> positionenListe = new ArrayList<>();
	
	private List<BestellungWrapper> bestellungListe = new ArrayList<>();
	
	private List<Produkt> namenListe = new ArrayList<>();

	@FXML
	private TextField tischNrInput;

	@FXML
	private TableView<BestellungPositionWrapper> bestellübersichtTbl;

	@FXML
	private TableColumn<BestellungPositionWrapper, String> colCodeUebersicht;

	@FXML
	private TableColumn<BestellungPositionWrapper, String> colBezeichnungUebersicht;
	
	@FXML
	private TableColumn<BestellungPositionWrapper, Double> colPreisUebersicht;
	
	@FXML
	private TableColumn<BestellungPositionWrapper, Integer> colAnzalUebersicht;

	@FXML
	private Button bestellungAbschickenBtn;

	@FXML
	private TextField bemerkungInput;

	@FXML
	private Button zurückInput;
	
	@FXML
	private Button bestellPositionLoeschenBtn;

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

	@FXML
	void bestellungAbschicken(ActionEvent event) {
		Bestellung bestellung = new Bestellung();

		try {

			Context.getInstance().getBestellungService().bestellen(bestellung);

		} catch (Exception e) {
			e.getMessage();
			System.out.println("Ein Fehler ist aufgetreten");
		}

	}

	
	@FXML
	void bestellPositionLoeschen(ActionEvent event) {
		
	}

	@FXML
	void gerichtSuchen(ActionEvent event) {

		String gerichtCode = gerichtNrInput.getText();
		Produkt p;

		try {

			p = Context.getInstance().getProduktService().findByProduktCode(gerichtCode);

			if (p == null) {
				gerichtNrInput.setText("Keine gültige Nummer");
				tblGerichtAuswahl.getItems().clear();
				
			} else {

				ProduktWrapper pWrapper = new ProduktWrapper(p);
				gefundesProdukt = pWrapper;

				gerichtNrInput.setText("");
				
				updateTableCodeSuche();
				
				

			}

		} catch (Exception e) {
			e.getMessage();
			System.out.println("Ein Fehler ist aufgetreten");
		}
	}

	@FXML
	void gerichtSuchenName(ActionEvent event) {

		String gerichtName = gerichtNameInput.getText();
		

		try {

			namenListe = Context.getInstance().getProduktService().findProduktByName(gerichtName);
			
			if(namenListe.isEmpty() == true) {
				gerichtNameInput.setText("Kein gültiger Name");
				tblGerichtAuswahl.getItems().clear();
			}else {
				
				gerichtNameInput.setText("");

				updateTableNameSuche();
			
			}
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Ein Fehler ist aufgetreten");
		}
	}

	@FXML
	void zuruck(ActionEvent event) {
		try {
			AnchorPane mainRoot = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
			Scene mainScene = new Scene(mainRoot);

			Stage mainStage = Context.getInstance().getMainStage();

			mainStage.setScene(mainScene);
			mainStage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
	}

	public void initialize(URL location, ResourceBundle resources) {

		try {
			colPreis.setCellValueFactory(new PropertyValueFactory<ProduktWrapper, Double>("preis"));
			colName.setCellValueFactory(new PropertyValueFactory<ProduktWrapper, String>("name"));
			colCodeUebersicht.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, String>("produktCode"));
			colBezeichnungUebersicht.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, String>("name"));
			colPreisUebersicht.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Double>("preis"));
			colAnzalUebersicht.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Integer>("anzahl"));
			
			
			updateTableNameSuche();
			updateTableCodeSuche();
			
			
			tblGerichtAuswahl.setRowFactory(new Callback<TableView<ProduktWrapper>, TableRow<ProduktWrapper>>() {

				@Override
				public TableRow<ProduktWrapper> call(TableView<ProduktWrapper> param) {
					TableRow<ProduktWrapper> tRow = new TableRow<>();

					tRow.setOnMouseClicked(event -> {
						if (event.getClickCount() == 1 && !tRow.isEmpty()) {
							ProduktWrapper item = tRow.getItem();

							Produkt produkt = item.getProdukt();

							if (produkt != null) {
								position = new BestellungPositionWrapper(new BestellungPosition(produkt, 1));
								
								bestellübersichtTbl.getItems().add(position);
								bestellübersichtTbl.getSelectionModel().select(0);
							
							}
						}
					});

					return tRow;
				}
				
			}
		);

		} catch (Exception e) {
			logger.error("Fehler beim initialisieren: ", e);
			throw new RuntimeException(e);
		}

	}

	public void updateTableCodeSuche() {

		try {

			tblGerichtAuswahl.getItems().clear();
			produktListe.clear();

			tblGerichtAuswahl.getItems().addAll(gefundesProdukt);
			tblGerichtAuswahl.getSelectionModel().select(0);

			gefundesProdukt = null;

		} catch (Exception e) {
			logger.error("Fehler beim updaten der Tabelle: ", e);
			throw new RuntimeException(e);
		}

	}
	
	public void updateTableNameSuche() {

//		List<Produkt> gefundeneProdukte = new ArrayList<>();
		
		try {
			
			tblGerichtAuswahl.getItems().clear();
			produktListe.clear();

			for (Produkt b : namenListe){
                produktListe.add(new ProduktWrapper(b));
            }			
			
			tblGerichtAuswahl.getItems().addAll(produktListe);
			tblGerichtAuswahl.getSelectionModel().select(0);

			gefundesProdukt = null;

		} catch (Exception e) {
			logger.error("Fehler beim updaten der Tabelle: ", e);
			throw new RuntimeException(e);
		}

	}
}
