package ch.hslu.informatik.gastgewerbe.gui.verwaltung;

import ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper.TischWrapper;
import ch.hslu.informatik.gastgewerbe.model.Tisch;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TischVerwaltenViewController implements Initializable {

    private static Logger logger = LogManager.getLogger(TischVerwaltenViewController.class);

    @FXML
    private TableView<TischWrapper> tblTisch;

    @FXML
    private TableColumn<TischWrapper, Integer> colTischNr;

    @FXML
    private Label lblError;

    @FXML
    private Button btnLoeschen;

    @FXML
    private Button btnReset;

    @FXML
    private TextField txtTischNummer;

    @FXML
    private TableColumn<TischWrapper, Integer> colNummer;

    public void initialize(URL location, ResourceBundle resources) {

        try {

            lblError.setText("");

            /* Tabelle konfigurieren */

            colNummer.setCellValueFactory(new PropertyValueFactory<TischWrapper, Integer>("nummer"));
            colTischNr.setCellValueFactory(new PropertyValueFactory<TischWrapper, Integer>("tischNr"));

            tblTisch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TischWrapper>() {

                @Override
                public void changed(ObservableValue<? extends TischWrapper> observable, TischWrapper oldValue,
                                    TischWrapper newValue) {
                    if (newValue != null) {
                        updateView();
                    }
                }
            });

            updateTabelle();

            btnLoeschen.disableProperty()
                   .bind(Bindings.size(tblTisch.getSelectionModel().getSelectedItems()).isEqualTo(0));

            updateTabelle();
        } catch (Exception e) {
            logger.error("Fehler bei der Initialisierung der View: ", e);
            return;
        }
    }

    private void updateTabelle() {

        lblError.setText("");

        try {
            List<Tisch> tischListe = Context.getInstance().getTischService().alleTische();

            if (tischListe.size() > 0) {
                List<TischWrapper> wrapperListe = new ArrayList<>();

                int nummer = 1;

                for (Tisch tisch : tischListe) {
                    wrapperListe.add(new TischWrapper(tisch, nummer++));
                }

                tblTisch.getItems().clear();
                tblTisch.getItems().addAll(wrapperListe);

                tblTisch.getSelectionModel().select(0);

                updateView();
            }
        } catch (Exception e) {
            logger.error("Fehler bei der Aktualisierung der Tabelle: ", e);
            throw new RuntimeException(e);
        }

    }

    private void updateView() {

        lblError.setText("");

        if (tblTisch.getSelectionModel().getSelectedItem() == null) {

            txtTischNummer.setText("0");

        } else {

            Tisch tisch = tblTisch.getSelectionModel().getSelectedItem().getTisch();

            txtTischNummer.setText("" + tisch.getTischNr());

        }

    }

    @FXML
    private void neuenTischErfassen() {
        reset();
        txtTischNummer.requestFocus();
    }

    @FXML
    private void speichern() {

            if (tblTisch.getSelectionModel().getSelectedItem() == null) {

                int tischNr = Integer.parseInt(txtTischNummer.getText());

                Tisch tisch = new Tisch(tischNr);

                try {
                    Context.getInstance().getTischService().tischHinzufuegen(tisch);

                } catch (Exception e) {
                    logger.error("Fehler beim Hinzufügen des Tisches: ", e);
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Tisch speichern");
                    alert.setHeaderText("Information");
                    alert.setContentText("Das Hinzufügen des neuen Tisch ist misslungen.");
                    alert.showAndWait();
                }

            } else {

                int tischNr = Integer.parseInt(txtTischNummer.getText());
                Tisch tisch = tblTisch.getSelectionModel().getSelectedItem().getTisch();
                tisch.setTischNr(tischNr);

                try {
                    Context.getInstance().getTischService().tischAktualisieren(tisch);
                } catch (Exception e) {
                    logger.error("Fehler beim Hinzufügen eines neuen Tisch: ", e);
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Tisch speichern");
                    alert.setHeaderText("Information");
                    alert.setContentText("Das Aktualisieren des ausgewählten Tisch ist misslungen.");
                    alert.showAndWait();
                }
            }

        updateTabelle();
        reset();
        txtTischNummer.requestFocus();

    }

    @FXML
    private void reset() {

        tblTisch.getSelectionModel().clearSelection();
        txtTischNummer.setText("0");
    }

    @FXML
    private void loeschen() {

        if (tblTisch.getSelectionModel().getSelectedItem() == null) {
            return ;
        }

        Tisch tisch = tblTisch.getSelectionModel().getSelectedItem().getTisch();

        if (tisch != null) {
            try {
                Context.getInstance().getTischService().tischLoeschen(tisch);
                updateTabelle();
                //updateView();
            } catch (Exception e) {
                logger.error("Fehler beim Löschen des Tisch: ", e);
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Tisch löschen");
                alert.setHeaderText("Information");
                alert.setContentText("Das Löschen des Tisch ist misslungen.");
                alert.showAndWait();
            }
        }
    }

}
