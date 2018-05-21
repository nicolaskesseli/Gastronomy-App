package ch.hslu.informatik.gastgewerbe.gui.verwaltung;

import ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper.BenutzerWrapper;
import ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper.TischWrapper;
import ch.hslu.informatik.gastgewerbe.model.*;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TischVerwaltenViewController implements Initializable {

    public static final String ERROR_MSG_PLZ_EINGABE_NICHT_KORREKT = "Die Eingabe für Postleitzahl ist nicht korrekt.";
    public static final String ERROR_MSG_EINGABE_NICHT_KORREKT = "Die Eingabe ist entweder nicht vollständig oder nicht korrekt (alle Felder sind 'required')";

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
    private Button btnHinzufuegen;

    // @Override
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            lblError.setText("");

            /* Tabelle konfigurieren */
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

        } catch (Exception e) {
            logger.error("Fehler bei der Initialisierung der View: ", e);
            return;
        }
    }

    private void updateTabelle() {

        try {
            List<Tisch> tischList = Context.getInstance().;

            if (benutzerListe.size() > 0) {
                List<BenutzerWrapper> wrapperListe = new ArrayList<>();

                int nummer = 1;

                for (Benutzer benutzer : benutzerListe) {
                    wrapperListe.add(new BenutzerWrapper(nummer++, benutzer));
                }

                tblBenutzer.getItems().clear();
                tblBenutzer.getItems().addAll(wrapperListe);

                tblBenutzer.getSelectionModel().select(0);

                updateView();
            }
        } catch (Exception e) {
            logger.error("Fehler bei der Aktualisierung der Tabelle: ", e);
            throw new RuntimeException(e);
        }

    }

    private void updateView() {

        lblError.setText("");

        if (tblBenutzer.getSelectionModel().getSelectedItem() == null) {

            cmbRolle.getSelectionModel().clearSelection();
            txtName.setText("");
            txtVorname.setText("");
            txtStrasse.setText("");
            txtPlz.setText("");
            txtOrt.setText("");
            txtEmail.setText("");
            txtTelefon.setText("");
            txtBenutzername.setText("");
            txtKennwort.setText("");

        } else {

            Benutzer benutzer = tblBenutzer.getSelectionModel().getSelectedItem().getBenutzer();

            cmbRolle.getSelectionModel().select(benutzer.getRolle());
            txtName.setText(benutzer.getNachname());
            txtVorname.setText(benutzer.getVorname());
            txtStrasse.setText(benutzer.getAdresse().getStrasse());
            txtPlz.setText("" + benutzer.getAdresse().getPlz());
            txtOrt.setText(benutzer.getAdresse().getOrt());
            txtEmail.setText(benutzer.getKontakt().getEmail());
            txtTelefon.setText(benutzer.getKontakt().getTelefon());
            txtBenutzername.setText(benutzer.getCredentials().getBenutzername());
            txtKennwort.setText(benutzer.getCredentials().getPasswort());
        }

    }

    @FXML
    private void neuenBenutzerErfassen() {
        reset();
        txtName.requestFocus();
    }

    @FXML
    private void speichern() {

        if (eingabeValid()) {

            if (tblBenutzer.getSelectionModel().getSelectedItem() == null) {

                /* Neuen Benutzer einfügen */
                String name = txtName.getText();
                String vorname = txtVorname.getText();
                String strasse = txtStrasse.getText();
                int plz = Integer.parseInt(txtPlz.getText());
                String ort = txtOrt.getText();
                String email = txtEmail.getText();
                String telefon = txtTelefon.getText();
                String benutzername = txtBenutzername.getText();
                String kennwort = txtKennwort.getText();
                RolleTyp rolle = cmbRolle.getSelectionModel().getSelectedItem();

                Benutzer benutzer = new Benutzer(name, vorname, new Adresse(strasse, plz, ort),
                        new Kontakt(email, telefon), new Credentials(benutzername, kennwort), rolle);

                try {
                    Context.getInstance().getBenutzerService().benutzerHinzufuegen(benutzer);
                } catch (Exception e) {
                    logger.error("Fehler beim Hinzufügen eines neuen Benutzers: ", e);
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Benutzer speichern");
                    alert.setHeaderText("Information");
                    alert.setContentText("Das Hinzufügen des neuen Benutzers ist misslungen.");
                    alert.showAndWait();
                }
            } else {

                /* Den selektierten Benutzer updaten */
                String name = txtName.getText();
                String vorname = txtVorname.getText();
                String strasse = txtStrasse.getText();
                int plz = Integer.parseInt(txtPlz.getText());
                String ort = txtOrt.getText();
                String email = txtEmail.getText();
                String telefon = txtTelefon.getText();
                String benutzername = txtBenutzername.getText();
                String kennwort = txtKennwort.getText();
                RolleTyp rolle = cmbRolle.getSelectionModel().getSelectedItem();

                Benutzer benutzer = tblBenutzer.getSelectionModel().getSelectedItem().getBenutzer();

                benutzer.setNachname(name);
                benutzer.setVorname(vorname);
                benutzer.setAdresse(new Adresse(strasse, plz, ort));
                benutzer.setKontakt(new Kontakt(email, telefon));
                benutzer.getCredentials().setBenutzername(benutzername);
                benutzer.getCredentials().setPasswort(kennwort);
                benutzer.setRolle(rolle);

                try {
                    Context.getInstance().getBenutzerService().benutzerAktualisieren(benutzer);
                } catch (Exception e) {
                    logger.error("Fehler beim Hinzufügen eines neuen Benutzers: ", e);
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Benutzer speichern");
                    alert.setHeaderText("Information");
                    alert.setContentText("Das Aktualisieren des ausgewählten Benutzers ist misslungen.");
                    alert.showAndWait();
                }
            }

            updateTabelle();
            reset();
            txtName.requestFocus();
        }
    }

    private boolean eingabeValid() {

        lblError.setText("");
        if (isValid(txtName.getText()) && isValid(txtVorname.getText()) && isValid(txtStrasse.getText())
                && isValid(txtPlz.getText()) && isValid(txtOrt.getText()) && isValid(txtEmail.getText())
                && isValid(txtTelefon.getText()) && isValid(txtEmail.getText()) && isValid(txtKennwort.getText())) {

            /* Prüfen, od die PLZ korrekt eingegeben wurde */
            try {
                Integer.parseInt(txtPlz.getText());
                return true;
            } catch (NumberFormatException e) {
                lblError.setText(ERROR_MSG_PLZ_EINGABE_NICHT_KORREKT);
                return false;
            }
        } else {
            lblError.setText(ERROR_MSG_EINGABE_NICHT_KORREKT);
            return false;
        }

    }

    @FXML
    private void reset() {

        tblBenutzer.getSelectionModel().clearSelection();

        cmbRolle.getSelectionModel().select(0);

        txtName.setText("");
        txtVorname.setText("");
        txtStrasse.setText("");
        txtPlz.setText("");
        txtOrt.setText("");
        txtEmail.setText("");
        txtTelefon.setText("");
        txtBenutzername.setText("");
        txtKennwort.setText("");
    }

    @FXML
    private void loeschen() {

        if (tblBenutzer.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        Benutzer benutzer = tblBenutzer.getSelectionModel().getSelectedItem().getBenutzer();

        if (benutzer != null) {
            try {
                Context.getInstance().getBenutzerService().benutzerLoeschen(benutzer);
                updateTabelle();
                updateView();
            } catch (Exception e) {
                logger.error("Fehler beim Löschen des Benutzers: ", e);
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Benutzer löschen");
                alert.setHeaderText("Information");
                alert.setContentText("Das Löschen des Benutzers ist misslungen.");
                alert.showAndWait();
            }
        }
    }

    private boolean isValid(String str) {
        return str != null && str.trim().length() > 0;
    }

    @FXML
    void zurück(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/VerwaltungHomeView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            //		stage.initModality(Modality.APPLICATION_MODAL);
            //		stage.initStyle(StageStyle.UNDECORATED);
            //		stage.setTitle("Hauptseite");
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
