package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungPositionWrapper;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.ProduktWrapper;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.model.Tisch;

public class BestellungErfassenController implements Initializable {

	private static Logger logger = LogManager.getLogger(BestellungErfassenController.class);

	private BestellungPositionWrapper position;

	private int tischNr;

	private List<ProduktWrapper> produktListe = new ArrayList<>();

	private List<Produkt> namenListe = new ArrayList<>();

	private BestellungPositionWrapper position2;

	
	 public static final String ERROR_MSG_OFFENE_BESTELLUNG_TRUE = "Es gibt bereits eine offene Bestellung unter dem ausgewählten Tisch";
	 public static final String ERROR_MSG_GANZE_ZAHL_EINGEBEN = "Ungültige(s) Zahlenformat / Tischnummer. Neue Eingabe";
	 
	@FXML
	private TextField tischNrInput;
	
		
	@FXML
	private TextField inputAnzahl;

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
	private Button BtnBestellungAktualisieren;

	@FXML
	private Button BtnBestellungAnzeigen;

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
	void bestellPositionLoeschen(ActionEvent event) {

		bestellPositionLoeschenBtn.setOnAction(e -> {
			BestellungPositionWrapper selectedItem = bestellübersichtTbl.getSelectionModel().getSelectedItem();
			bestellübersichtTbl.getItems().remove(selectedItem);
		});
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
			logger.error("Fehler beim Gericht suchen (nach Code): ", e);
			throw new RuntimeException(e);
		}
	}

	@FXML
	void gerichtSuchenName(ActionEvent event) {

		String gerichtName = gerichtNameInput.getText();

		try {

			namenListe = Context.getInstance().getProduktService().findProduktByName(gerichtName);

			if (namenListe.isEmpty() == true) {
				gerichtNameInput.setText("Kein gültiger Name");
				tblGerichtAuswahl.getItems().clear();
			} else {

				gerichtNameInput.setText("");

				updateTableNameSuche();

			}
		} catch (Exception e) {
			logger.error("Fehler beim Gericht suchen nach Name: ", e);
			throw new RuntimeException(e);
		}
	}

	@FXML
	void bestellungAnzeigen(ActionEvent event) {

		
			
		try {
			bestellübersichtTbl.getItems().clear();
			bemerkungInput.setText("");
			inputAnzahl.setText("1");
			int tischNr = Integer.parseInt(tischNrInput.getText());

			List<Bestellung> list = Context.getInstance().getBestellungService().findByRechBezahltTisch(tischNr, false);
			if (list.isEmpty()) {
		
				
				Alert error = new Alert(Alert.AlertType.ERROR);
				error.setTitle("ACHTUNG");
				error.setHeaderText("Kein/e Tisch / Bestellung vorhanden!");
				error.setContentText("Angegebene Tisch-Nr. ungültig oder keine offene Bestellung vorhanden. Neue Eingabe");
				error.showAndWait();
			} else {
				
				Bestellung bestellung = list.get(0);

				List<BestellungPosition> pList = bestellung.getBestellungPositionListe();

				for (int i = 0; i < pList.size(); i++) {
					position2 = new BestellungPositionWrapper(pList.get(i));
					bestellübersichtTbl.getItems().add(position2);
				}

				bemerkungInput.setText(bestellung.getBemerkung());
			}

		} catch (NumberFormatException e) {
			Alert error = new Alert(Alert.AlertType.ERROR);
			error.setTitle("ACHTUNG");
			error.setHeaderText("Ungültiges Zahlenformat");
			error.setContentText("Geben Sie eine gültige Tisch-Nr. ein");
			error.showAndWait();
		
		} catch (Exception e) {
			logger.error("Fehler bei einer bestehenden Bestellung bearbeiten ", e);
			throw new RuntimeException(e);
		}
	}

	public void bestellungAktualisieren(ActionEvent event) throws Exception {
				
		try {

			String bemerkung = bemerkungInput.getText();

			if (bemerkung.isEmpty()) {
				bemerkung = "";
			}

			if (tischNrInput.getText().isEmpty()) {

				tischNrInput.setText("Tisch-Nr. eingeben");

			} else if (bestellübersichtTbl.getItems().isEmpty()) {

				Alert error = new Alert(Alert.AlertType.ERROR);
				error.setTitle("ACHTUNG");
				error.setHeaderText("Bestellung leer!");
				error.setContentText("Bitte Bestellposition hinzufügen");
				error.showAndWait();

			} else {

				tischNr = Integer.parseInt(tischNrInput.getText());
				
				Tisch tisch = Context.getInstance().getTischService().findByTischNummer(tischNr);
				if (!(tisch != null)) {
					Alert error = new Alert(Alert.AlertType.ERROR);
					error.setTitle("ACHTUNG");
					error.setHeaderText("Tisch nicht vorhanden!");
					error.setContentText("Geben Sie eine gültige Tisch-Nr. ein");
					error.showAndWait();;
				} else {
					
				

				
				Bestellung bestellung = new Bestellung(bemerkung,
				Context.getInstance().getTischService().findByTischNummer(tischNr), LocalDateTime.now());
				
				

				for (BestellungPositionWrapper item : bestellübersichtTbl.getItems()) {
					bestellung.getBestellungPositionListe().add(item.getBestellungPosition());

				}
				Context.getInstance().getBestellungService().bestellungAktualisieren(bestellung);

				bemerkungInput.clear();
				tischNrInput.clear();
				bestellübersichtTbl.getItems().clear();
				tblGerichtAuswahl.getItems().clear();
				inputAnzahl.setText("1");

			}}

		} catch (NumberFormatException e) {
			String msg = "Keine Nummer im Eingabefeld.";
			String ausgabe = "Nummer eingeben!";
			tischNrInput.setText(ausgabe);
			throw new Exception(msg);

		} catch (Exception e) {
			String msg = "Ein Fehler ist bei Bestellungübergabe aufgetreten";
			logger.error(msg, e);
			throw new Exception(msg, e);

		}
	}

	@FXML
	void zuruck(ActionEvent event) throws Exception {
		try {
			AnchorPane backRoot = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

			Scene backScene = new Scene(backRoot);

			Stage mainStage = Context.getInstance().getMainStage();

			mainStage.setScene(backScene);
			mainStage.show();

		} catch (IOException e) {
			String msg = "Ein Fehler ist beim Laden der letzten Seite aufgetreten";
			logger.error(msg, e);
			throw new Exception(msg, e);

		}

	}

	public void initialize(URL location, ResourceBundle resources) {
		bestellübersichtTbl.setEditable(true);
		try {
			colPreis.setCellValueFactory(new PropertyValueFactory<ProduktWrapper, Double>("preis"));
			colName.setCellValueFactory(new PropertyValueFactory<ProduktWrapper, String>("name"));
			colCodeUebersicht
					.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, String>("produktCode"));
			colBezeichnungUebersicht
					.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, String>("name"));
			colPreisUebersicht
					.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Double>("preis"));

			

			colAnzalUebersicht
					.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Integer>("anzahl"));
			
			
			

			updateTableNameSuche();
			updateTableCodeSuche();

			tblGerichtAuswahl.setRowFactory(new Callback<TableView<ProduktWrapper>, TableRow<ProduktWrapper>>() {

				@Override
				public TableRow<ProduktWrapper> call(TableView<ProduktWrapper> param) {
					TableRow<ProduktWrapper> tRow = new TableRow<>();

					tRow.setOnMouseClicked(event -> {

						if (event.getClickCount() == 2 && !tRow.isEmpty()) {
							if (tRow.getItem() == null) {

							} else {

								ProduktWrapper item = tRow.getItem();

								Produkt produkt = item.getProdukt();

								if (produkt != null) {
									
									int anzahl = Integer.parseInt(inputAnzahl.getText());
									
									position = new BestellungPositionWrapper(new BestellungPosition(produkt, anzahl));

									bestellübersichtTbl.getItems().add(position);
									bestellübersichtTbl.getSelectionModel().select(0);

								}
							}

						}
					});

					return tRow;
				}

			});



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

		try {

			tblGerichtAuswahl.getItems().clear();
			produktListe.clear();

			for (Produkt b : namenListe) {
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

	@FXML
	void bestellungAbschicken(ActionEvent event) throws Exception {

		try {
			
			

			String bemerkung = bemerkungInput.getText();

			if (bemerkung.isEmpty()) {
				bemerkung = "";
			}

			if (tischNrInput.getText().isEmpty()) {

				tischNrInput.setText("Tisch-Nr. eingeben.");

			} else if (bestellübersichtTbl.getItems().isEmpty()) {

				Alert error = new Alert(Alert.AlertType.ERROR);
				error.setTitle("ACHTUNG");
				error.setHeaderText("Bestellung leer!");
				error.setContentText("Bitte Bestellposition hinzufügen");
				error.showAndWait();

			} else {

				
				tischNr = Integer.parseInt(tischNrInput.getText());

				
				Tisch tisch = Context.getInstance().getTischService().findByTischNummer(tischNr);
				
				if (!(tisch != null)) {
			
					Alert error = new Alert(Alert.AlertType.ERROR);
					error.setTitle("ACHTUNG");
					error.setHeaderText("Tisch nicht vorhanden!");
					error.setContentText("Geben Sie eine gültige Tisch-Nr. ein");
					error.showAndWait();
					
				} else if (!Context.getInstance().getBestellungService().findByRechBezahltTisch(tischNr, false).isEmpty()) {
					bestellungAnzeigen(event);
		
					
					Alert error = new Alert(Alert.AlertType.ERROR);
					error.setTitle("ACHTUNG");
					error.setHeaderText("Offene Bestellung vorhanden");
					error.setContentText("Schliessen Sie die Bestellung ab oder bearbeiten Sie die bestehende.");
					error.showAndWait();
				
								
				} else {
					
				
				
				Bestellung b = new Bestellung(bemerkung,
						Context.getInstance().getTischService().findByTischNummer(tischNr), LocalDateTime.now());

				for (BestellungPositionWrapper item : bestellübersichtTbl.getItems()) {
					b.getBestellungPositionListe().add(item.getBestellungPosition());

				}

				Context.getInstance().getBestellungService().bestellen(b);
				
				bemerkungInput.clear();
				tischNrInput.clear();
				bestellübersichtTbl.getItems().clear();
				tblGerichtAuswahl.getItems().clear();
				inputAnzahl.setText("1");
				gerichtNameInput.clear();
				gerichtNrInput.clear();
				}
			}

		} catch (NumberFormatException e) {
			String msg = "Keine Nummer im Eingabefeld.";
			String ausgabe = "Nummer eingeben!";
			tischNrInput.setText(ausgabe);
			throw new Exception(msg);

		} catch (Exception e) {
			String msg = "Ein Fehler ist bei Bestellungübergabe aufgetreten";
			logger.error(msg, e);
			throw new Exception(msg, e);

		}

	}
	

}
