package ch.hslu.informatik.gastgewerbe.gui.verwaltung;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarViewController implements Initializable {

    private static final String TITEL_BENUTZER_VERWALTEN = "Benutzer verwalten";
    private static final String TITEL_BESTELLUNGEN_ANSCHAUEN = "Bestellübersicht";
    private static final String TITEL_PRODUKT_VERWALTEN = "Produkte verwalten";
    private static final String TITEL_TISCH_VERWALTEN = "Tische verwalten";
    private static final String TITEL_ABRECHNUNG_VERWALTEN = "Tagesabrechnung";

    @FXML
    private Label lblBenutzer;

    @FXML
    private Label lblTitel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String str = Context.getInstance().getBenutzer().getVorname() + " "
                + Context.getInstance().getBenutzer().getNachname();
        lblBenutzer.setText("Angemeldet: " + str);
        lblBenutzer.setAlignment(Pos.BASELINE_RIGHT);

        Context.getInstance().setMenuBarViewController(this);
    }

    @FXML
    public void home() {

        lblTitel.setText("");

        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/VerwaltungHomeView.fxml"));
            Context.getInstance().getMainRoot().setCenter(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void bestellungAnschauen() {

        lblTitel.setText(TITEL_BESTELLUNGEN_ANSCHAUEN);

        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/BestellungView.fxml"));
            Context.getInstance().getMainRoot().setCenter(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void produktVerwalten() {

        lblTitel.setText(TITEL_PRODUKT_VERWALTEN);

        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/ProduktVerwaltenView.fxml"));
            Context.getInstance().getMainRoot().setCenter(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void benutzerVerwalten() {

        lblTitel.setText(TITEL_BENUTZER_VERWALTEN);

        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/BenutzerVerwaltenView.fxml"));
            Context.getInstance().getMainRoot().setCenter(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void tischVerwalten() {

        lblTitel.setText(TITEL_TISCH_VERWALTEN);

        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/TischView.fxml"));
            Context.getInstance().getMainRoot().setCenter(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void abrechnungVerwaltung() {

        lblTitel.setText(TITEL_ABRECHNUNG_VERWALTEN);

        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/AbrechnungView.fxml"));
            Context.getInstance().getMainRoot().setCenter(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void abmelden() {

        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
            Context.getInstance().getMainRoot().setCenter(root);
            Context.getInstance().getMainRoot().setTop(null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
