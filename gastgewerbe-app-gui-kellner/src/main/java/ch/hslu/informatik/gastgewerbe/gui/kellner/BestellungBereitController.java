package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungPositionWrapper;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungWrapper;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import javafx.beans.binding.Bindings;
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

public class BestellungBereitController extends TimerTask implements Initializable {

   	private static Logger logger = LogManager.getLogger(BestellungBereitController.class);

    private List<BestellungWrapper> bestellungenListe = new ArrayList<>();

    private List<BestellungPositionWrapper> positionenListe = new ArrayList<>();

    @FXML
    private Button BestellPosAusgelieferBtn;

    @FXML
    private TableView<BestellungWrapper> tblBestKellner;

    @FXML
    private TableColumn<BestellungWrapper, Integer> colTischNr;

    @FXML
    private TableColumn<BestellungWrapper, LocalDateTime> colZeit;

    @FXML
    private TableColumn<BestellungWrapper, String> colBemerkung;

    @FXML
    private TableView<BestellungPositionWrapper> tblPosKellner;

    @FXML
    private TableColumn<BestellungPositionWrapper, Long> colPos;

    @FXML
    private TableColumn<BestellungPositionWrapper, Integer> colAnzahl;

    @FXML
    private TableColumn<BestellungPositionWrapper, String> colProdukt;

    @FXML
    void bestellPosAusgeliefert(ActionEvent event) {

        BestellungPositionWrapper ausgewähltePosition;

        try{
            ausgewähltePosition=tblPosKellner.getSelectionModel().getSelectedItem();

            if(ausgewähltePosition!= null){
                BestellungPosition ausgeliefert = Context.getInstance().getBestellungService().bestPosFindById(ausgewähltePosition.getId());

                Context.getInstance().getBestellungService().bestellungPositionAusgeliefert(ausgeliefert);
                tblPosKellner.getItems().remove(ausgewähltePosition);
            }

            updateTable();

        }catch (Exception e) {
            String msg = "BestellPos. konnte nicht in Status ausgeliefert versetzt werden!";
            logger.error(msg, e);

            // error dialog anzeigen
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Fehler");
            error.setHeaderText("Fehler bei Statusänderung");
            error.setContentText("Die ausgewählte Position konnte nicht in den Status ausgeliefert versetzt werden. Bitte wenden Sie sich an Ihren Systemadministrator.");
            error.showAndWait();
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


    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        try {

            updateTable();

        } catch (Exception e){
            logger.info("Tabelle Update fehlgeschlagen. Grund: keine bereiten Bestellungen" + e);
        }
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Zeit formatieren

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");

        try {

            // Tabelle initialisieren
            colTischNr.setCellValueFactory(new PropertyValueFactory<BestellungWrapper, Integer>("tischNr"));
            colZeit.setCellValueFactory(new PropertyValueFactory<BestellungWrapper, LocalDateTime>("zeit"));
            colBemerkung.setCellValueFactory(new PropertyValueFactory<BestellungWrapper, String>("bemerkung"));

            colPos.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Long>("id"));
            colAnzahl.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Integer>("anzahl"));
            colProdukt.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, String>("produkt"));

            // Datumformat anpassen CellFactory anpassen um nach dateFormatter zu formatieren

            colZeit.setCellFactory(
                    new Callback<TableColumn<BestellungWrapper, LocalDateTime>, TableCell<BestellungWrapper, LocalDateTime>>() {

                        @Override
                        public TableCell<BestellungWrapper, LocalDateTime> call(TableColumn<BestellungWrapper, LocalDateTime> param) {

                            return new TableCell<BestellungWrapper, LocalDateTime>() {

                                protected void updateItem(LocalDateTime item, boolean empty) {
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

            /* Auf Click werden die Positionen der ausgewählten Bestellung angezeigt*/
            tblBestKellner.setRowFactory(new Callback<TableView<BestellungWrapper>, TableRow<BestellungWrapper>>() {

                @Override
                public TableRow<BestellungWrapper> call(TableView<BestellungWrapper> param) {
                    TableRow<BestellungWrapper> tRow = new TableRow<>();

                    tRow.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 1 && !tRow.isEmpty()) {
                            BestellungWrapper item = tRow.getItem();

                            Bestellung bestellung = item.getBestellung();

                            if (bestellung != null) {

                                tblPosKellner.getItems().clear();
                                positionenListe.clear();

                                List<BestellungPosition> tempListe = bestellung.getBestellungPositionListe();
                                for (BestellungPosition p : tempListe) {
                                    if (!p.isBestellungAusgeliefert()) {
                                    	if (p.isBestellungBereit()) {
                                        positionenListe.add(new BestellungPositionWrapper(p));
                                    	}
                                    }
                                }
                                tblPosKellner.getItems().addAll(positionenListe);
                                tblPosKellner.getSelectionModel().select(0);
                            }
                        }
                    });

                    return tRow;
                }
            });

            // Tabelle aktualisieren
            updateTable();

            /* Binding für die Schaltfläche 'Ausgeliefert' erstellen */
            BestellPosAusgelieferBtn.disableProperty().bind(Bindings.size(tblBestKellner.getItems()).lessThan(1));

            // Automatisches aktualisieren
            BestellungBereitController task = new BestellungBereitController();
            Timer timer = new Timer();
            timer.schedule(task, 60000, 60000);

        } catch (Exception e) {
            logger.error("Fehler bei der View-Initialisierung: ", e);
            throw new RuntimeException(e);
        }
    }

    private void updateTable() {

        tblBestKellner.getItems().clear();;
        bestellungenListe.clear();

        try {
            // Alle Bestellungen einlesen die noch nicht ausgliefert sind
            List<Bestellung> alleBestellungen = Context.getInstance().getBestellungService().findByAusgeliefert(false);

            List<Bestellung> kellnerBestellungen = new ArrayList<>();

            for (Bestellung b : alleBestellungen){
                List<BestellungPosition> bestellungPosition = b.getBestellungPositionListe();
                for (BestellungPosition p : bestellungPosition) {
                        if(!p.isBestellungAusgeliefert()){
                        	if(p.isBestellungBereit()) {
                        		if(!kellnerBestellungen.contains(b)){
                                kellnerBestellungen.add(b);
                        		}
                            }
                        }
                }
            }
            for (Bestellung b : kellnerBestellungen){
                bestellungenListe.add(new BestellungWrapper(b));
            }



            tblBestKellner.getItems().addAll(bestellungenListe);
            tblBestKellner.getSelectionModel().select(0);

        } catch (Exception e) {
            logger.error("Fehler beim updaten der Tabelle: ", e);
            throw new RuntimeException(e);
        }
    }
}
