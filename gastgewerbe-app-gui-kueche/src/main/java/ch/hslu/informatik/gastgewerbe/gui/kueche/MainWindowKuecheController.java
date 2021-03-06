package ch.hslu.informatik.gastgewerbe.gui.kueche;



import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungPositionWrapper;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungWrapper;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MainWindowKuecheController extends TimerTask implements Initializable {

    private static Logger logger = LogManager.getLogger(MainWindowKuecheController.class);

    private List<BestellungWrapper> bestellungenListe = new ArrayList<>();

    private List<BestellungPositionWrapper> positionenListe = new ArrayList<>();

    @FXML
    private Button bestellungBereitBtn;

    @FXML
    private Button bestellungLöschenBtn;

    @FXML
    private TableView<BestellungWrapper> tblBestKueche;

    @FXML
    private TableColumn<BestellungWrapper, Integer> colTischNr;

    @FXML
    private TableColumn<BestellungWrapper, LocalDateTime> colZeit;

    @FXML
    private TableColumn<BestellungWrapper, String> colBemerkung;

    @FXML
    private TableView<BestellungPositionWrapper> tblPosKueche;

    @FXML
    private TableColumn<BestellungPositionWrapper, Long> colPos;

    @FXML
    private TableColumn<BestellungPositionWrapper, Integer> colAnzahl;

    @FXML
    private TableColumn<BestellungPositionWrapper, String> colProdukt;

    @FXML
    private Label lblBenutzer;

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
            throw new RuntimeException(e);

        }
    }
    @FXML
    void bestellungBereit(ActionEvent event) {

        BestellungPositionWrapper ausgewähltePosition;

        try{
            ausgewähltePosition=tblPosKueche.getSelectionModel().getSelectedItem();

            if(ausgewähltePosition!= null){
                BestellungPosition bereit = Context.getInstance().getBestellungService().bestPosFindById(ausgewähltePosition.getId());
                Context.getInstance().getBestellungService().bestellungPositionBereit(bereit);
                tblPosKueche.getItems().remove(ausgewähltePosition);
            }

            updateTable();


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

        BestellungWrapper ausgewahlteBestellung;

        try {
            ausgewahlteBestellung=tblBestKueche.getSelectionModel().getSelectedItem();

            if(ausgewahlteBestellung != null){
                Bestellung löschen = Context.getInstance().getBestellungService().findById(ausgewahlteBestellung.getId());
                Context.getInstance().getBestellungService().deleteBestellung(löschen);
            }

            tblPosKueche.getItems().clear();
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

        String str = Context.getInstance().getBenutzer().getVorname() + " "
                + Context.getInstance().getBenutzer().getNachname();
        lblBenutzer.setText("Angemeldet: " + str);
        lblBenutzer.setAlignment(Pos.BASELINE_CENTER);

        //Zeit formatieren

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");

        try{

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
            tblBestKueche.setRowFactory(new Callback<TableView<BestellungWrapper>, TableRow<BestellungWrapper>>() {

                @Override
                public TableRow<BestellungWrapper> call(TableView<BestellungWrapper> param) {
                    TableRow<BestellungWrapper> tRow = new TableRow<>();

                    tRow.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 1 && !tRow.isEmpty()) {
                            BestellungWrapper item = tRow.getItem();

                            Bestellung bestellung = item.getBestellung();

                            if (bestellung != null) {

                                tblPosKueche.getItems().clear();
                                positionenListe.clear();

                                List<BestellungPosition> tempListe = bestellung.getBestellungPositionListe();
                                for(BestellungPosition p : tempListe){
                                    if(p.getProdukt().getKategorie().equals(KategorieTyp.SPEISE)){
                                        if(!p.isBestellungBereit()){
                                            positionenListe.add(new BestellungPositionWrapper(p));
                                        }
                                    }
                                }
                                tblPosKueche.getItems().addAll(positionenListe);
                                tblPosKueche.getSelectionModel().select(0);
                            }
                        }
                    });

                    return tRow;
                }
            });


            // Tabelle aktualisieren
            updateTable();

            /* Binding für die Schaltfläche 'Löschen' erstellen */
            bestellungLöschenBtn.disableProperty().bind(Bindings.size(tblBestKueche.getItems()).lessThan(1));
            /* Binding für die Schaltfläche 'Bereit' erstellen */
            bestellungBereitBtn.disableProperty().bind(Bindings.size(tblPosKueche.getItems()).lessThan(1));


            // Automatisches aktualisieren
            MainWindowKuecheController task = new MainWindowKuecheController();
            Timer timer = new Timer();
            timer.schedule(task, 120000, 3000000);

        }catch (Exception e) {
            logger.error("Fehler bei der View-Initialisierung: ", e);
            throw new RuntimeException(e);
        }

    }

    private void updateTable() {

        tblBestKueche.getItems().clear();
        bestellungenListe.clear();

        try {
            // Nur Bestellungen für Küche einlesen D.h. Kategorie SPEISE die noch nicht bereit sind
            List<Bestellung> alleBestellungen = Context.getInstance().getBestellungService().findByBereit(false);

            List<Bestellung> kuecheBestellungen = new ArrayList<>();

            for (Bestellung b : alleBestellungen){
                List<BestellungPosition> bestellungPosition = b.getBestellungPositionListe();
                for (BestellungPosition p : bestellungPosition) {
                    if (p.getProdukt().getKategorie().equals(KategorieTyp.SPEISE)){
                        if(!p.isBestellungBereit()){
                            if(!kuecheBestellungen.contains(b)){
                                kuecheBestellungen.add(b);
                            }
                        }
                    }
                }
            }
            for (Bestellung b : kuecheBestellungen){
                bestellungenListe.add(new BestellungWrapper(b));
            }


            tblBestKueche.getItems().addAll(bestellungenListe);
            tblBestKueche.getSelectionModel().select(0);

        } catch (Exception e) {
            logger.error("Fehler beim updaten der Tabelle: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {

            updateTable();

        } catch (Exception e){
            logger.info("Tabelle Update fehlgeschlagen. Grund: keine nicht bereiten Bestellungen" + e);
        }
    }
}


