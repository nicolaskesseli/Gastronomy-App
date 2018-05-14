package ch.hslu.informatik.gastgewerbe.gui.bar;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainWindowBarController implements Initializable {

	private static Logger logger = LogManager.getLogger(MainWindowBarController.class);

	private List<Bestellung> bestellungen = new ArrayList<>();

	@FXML
	private TableView<Bestellung> bestellübersichtBarTbl;

	@FXML
	private TableColumn<Bestellung, Integer> colTischNr;

	@FXML
	private TableColumn<Bestellung, LocalDate> colZeit;

	@FXML
	private TableColumn<BestellungPosition, Integer> colAnz;

	@FXML
	private TableColumn<BestellungPosition, Produkt> colProdukt;

	@FXML
	private TableColumn<Bestellung, String> colBemer;
	
	@FXML 
	private Button bestellungBereitBtn;
	
	@FXML
	private Button bestellungLöschenBtn;
	
	@FXML
	private Button abmeldeBtn;
	
	@FXML
	void abmelden (ActionEvent event) {
		try {

			AnchorPane loginRoot = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));

			Scene loginScene = new Scene(loginRoot);

			Stage mainStage = Context.getInstance().getMainStage();

			mainStage.setScene(loginScene);
			mainStage.show();



		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
	}
	@FXML
        void bestellungBereit(ActionEvent event) {

        Bestellung ausgewahlteBestellung = bestellübersichtBarTbl.getSelectionModel().getSelectedItem();

        List<BestellungPosition> bestellungPosition = ausgewahlteBestellung.getBestellungPositionListe();



	    }

    @FXML
	    void bestellungLöschen(ActionEvent event) {

	    Bestellung ausgewahlteBestellung = bestellübersichtBarTbl.getSelectionModel().getSelectedItem();

	    try {

            Context.getInstance().getBestellungService().deletBestellung(ausgewahlteBestellung);

            updateTable();

            // info dialog anzeigen
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Bestellung gelöscht");
            info.setHeaderText("Information");
            info.setContentText("Die ausgewählte Bestellung wurde erfolgreich gelöscht.");
            info.showAndWait();


        } catch (Exception e) {
        String msg = "Bestellung: " +ausgewahlteBestellung.toString()+" konnte nicht gelöscht werden!";
        logger.error(msg, e);

        // error dialog anzeigen
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Fehler");
        error.setHeaderText("Fehler beim löschen");
        error.setContentText("Die ausgewählte Bestellung konnte nicht gelöscht werden. Bitte wenden Sie sich an Ihren Systemadministrator.");
        error.showAndWait();
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

		//Zeit formatieren

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");

	    try{

	    	// Tabelle initialisieren
	        colTischNr.setCellValueFactory(new PropertyValueFactory<Bestellung,Integer>("tischNr"));
	        colZeit.setCellValueFactory(new PropertyValueFactory<Bestellung, LocalDate>("zeit"));
	        colAnz.setCellValueFactory(new PropertyValueFactory<BestellungPosition, Integer>("anzahl"));
	        colProdukt.setCellValueFactory(new PropertyValueFactory<BestellungPosition, Produkt>("produkt"));
	        colBemer.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("bemerkung"));

	        // Datumformat anpassen CellFactory anpassen um nach dateFormatter zu formatieren

			colZeit.setCellFactory(new Callback<TableColumn<Bestellung, LocalDate>, TableCell<Bestellung, LocalDate>>() {
				@Override
				public TableCell<Bestellung, LocalDate> call(TableColumn<Bestellung, LocalDate> param) {
					return new TableCell<Bestellung, LocalDate>(){

						protected void updateItem(LocalDate item, boolean empty){
							super.updateItem(item, empty);

							if (item == null || empty) {
								setText(null);
							} else {
								setText(dateFormatter.format(item));
							}
						}
					};
				}
			});

			// Tabelle aktualisieren
			updateTable();

        }catch (Exception e) {
            logger.error("Fehler bei der View-Initialisierung: ", e);
            throw new RuntimeException(e);
        }

    }

    private void updateTable() {

        bestellübersichtBarTbl.getItems().clear();

        try {
            // Nur Bestellungen für Bar einlesen
            List<Bestellung> alleBestellungen = Context.getInstance().getBestellungService().alleBestellungen();

            for (Bestellung b : alleBestellungen) {
                List<BestellungPosition> bestellungPosition = b.getBestellungPositionListe();
                for (BestellungPosition p : bestellungPosition) {
                    if (p.getProdukt().getKategorie() == KategorieTyp.SPEISE || p.isBestellungBereit()==true) {
                        bestellungPosition.remove(p);
                    }
                }
            }



            bestellungen.addAll(alleBestellungen);
            bestellübersichtBarTbl.getItems().addAll(bestellungen);
            bestellübersichtBarTbl.getSelectionModel().select(0);

        } catch (Exception e) {
            logger.error("Fehler beim updaten der Tabelle: ", e);
            throw new RuntimeException(e);
        }
    }
}
