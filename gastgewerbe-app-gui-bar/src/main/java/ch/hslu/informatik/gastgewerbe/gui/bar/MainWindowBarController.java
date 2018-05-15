package ch.hslu.informatik.gastgewerbe.gui.bar;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainWindowBarController extends TimerTask implements Initializable {

	private static Logger logger = LogManager.getLogger(MainWindowBarController.class);

	private List<Bestellung> bestellungen = new ArrayList<>();

	@FXML
	private TableView<?> tblBar;

	@FXML
	private TableColumn<?, ?> colTischNr;

	@FXML
	private TableColumn<?, ?> colZeit;

	@FXML
	private TableColumn<?, ?> colBemerkung;

	@FXML
	private TableColumn<?, ?> colPos;

	@FXML
	private TableColumn<?, ?> colAnzahl;

	@FXML
	private TableColumn<?, ?> colProdukt;
	
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



        Bestellung ausgewahlteBestellung;

        try{
            ausgewahlteBestellung=TreeTableBar.getSelectionModel().getSelectedItem().getValue();

        	for (BestellungPosition p: ausgewahlteBestellung.getBestellungPositionListe()){
				Context.getInstance().getBestellungService().bestellungPositionBereit(p);
				updateTable();
			}
		} catch (Exception e) {
			String msg = "BestellPos. konnte nicht in Status bereit versetzt werden!";
			logger.error(msg, e);

			// error dialog anzeigen
			Alert error = new Alert(Alert.AlertType.ERROR);
			error.setTitle("Fehler");
			error.setHeaderText("Fehler bei Statusänderung");
			error.setContentText("Die ausgewählte Position konnte nicht in den Status bereit versetzt werden. Bitte wenden Sie sich an Ihren Systemadministrator.");
			error.showAndWait();
			}
	    }

    @FXML
	    void bestellungLöschen(ActionEvent event) {

	    Bestellung ausgewahlteBestellung;

	    try {
	        ausgewahlteBestellung=TreeTableBar.getSelectionModel().getSelectedItem().getValue();

            Context.getInstance().getBestellungService().deletBestellung(ausgewahlteBestellung);

            updateTable();

            // info dialog anzeigen
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Bestellung gelöscht");
            info.setHeaderText("Information");
            info.setContentText("Die ausgewählte Bestellung wurde erfolgreich gelöscht.");
            info.showAndWait();


        } catch (Exception e) {
        String msg = "Bestellung konnte nicht gelöscht werden!";
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

	        tcolTisch.setCellValueFactory(new TreeItemPropertyValueFactory<Bestellung,Integer>("tischNr"));
	        tcolZeit.setCellValueFactory(new TreeItemPropertyValueFactory<Bestellung, LocalDate>("zeit"));
	        tcolBemerkung.setCellValueFactory(new TreeItemPropertyValueFactory<Bestellung, String>("bemerkung"));
	        tcolPos.setCellValueFactory(new TreeItemPropertyValueFactory<BestellungPosition, Long>("id"));
	        tcolAnz.setCellValueFactory(new TreeItemPropertyValueFactory<BestellungPosition, Integer>("anzahl"));
	        tcolProdukt.setCellValueFactory(new TreeItemPropertyValueFactory<BestellungPosition, Produkt>("produkt"));

	        // Datumformat anpassen CellFactory anpassen um nach dateFormatter zu formatieren

			tcolZeit.setCellFactory(new Callback<TreeTableColumn<Bestellung, LocalDate>, TreeTableCell<Bestellung, LocalDate>>() {
				@Override
				public TreeTableCell<Bestellung, LocalDate> call(TreeTableColumn<Bestellung, LocalDate> param) {
					return new TreeTableCell<Bestellung, LocalDate>() {
						protected void updateItem(LocalDate item, boolean empty) {
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

			// Automatisches aktualisieren
            MainWindowBarController task = new MainWindowBarController();
            Timer timer = new Timer();
            timer.schedule(task, 120000, 3000000);

        }catch (Exception e) {
            logger.error("Fehler bei der View-Initialisierung: ", e);
            throw new RuntimeException(e);
        }

    }

    private void updateTable() {

        TreeTableBar.getColumns().clear();

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

			TreeItem<Bestellung> root = new TreeItem<>();

            for (Bestellung best : alleBestellungen){
				TreeItem<Bestellung> middleRoot = new TreeItem<>(best);
				middleRoot.setExpanded(true);

            	for(BestellungPosition bestelPosition: best.getBestellungPositionListe()){
            		Bestellung bestellung = new Bestellung();
            		List<BestellungPosition> einzelneBestPos = new ArrayList<>();
            		einzelneBestPos.add(bestelPosition);
            		bestellung.setBestellungPositionListe(einzelneBestPos);
            		TreeItem<Bestellung> childNode = new TreeItem<>(bestellung);
					middleRoot.getChildren().add(childNode);
				}
            	root.getChildren().add(middleRoot);
			}

			TreeTableBar.setRoot(root);
			TreeTableBar.setShowRoot(false);
			TreeTableBar.getSelectionModel().select(0);

        } catch (Exception e) {
            logger.error("Fehler beim updaten der Tabelle: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
	    updateTable();
    }
}
