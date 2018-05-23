package ch.hslu.informatik.gastgewerbe.gui.verwaltung;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper.ProduktWrapper;
import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ProduktVerwaltenViewController implements Initializable {

	private static Logger logger = LogManager.getLogger(ProduktVerwaltenViewController.class);

	public static final String ERROR_MSG_LOESCHEN_MISSLUNGEN = "Produkt-Typ konnte nicht gelöscht werden.";
	public static final String ERROR_MSG_SPEICHERN_MISSLUNGEN = "Produkt-Typ konnte nicht gespeichert werden.";
	public static final String ERROR_MSG_UPDATE_MISSLUNGEN = "Produkt-Typ konnte nicht updatet werden.";
	public static final String ERROR_MSG_PREIS_EINGABE_NICHT_KORREKT = "Die Preisangabe ist nicht korrekt.";
	public static final String ERROR_MSG_EINGABE_NICHT_KORREKT = "Die Eingabe ist entweder nicht vollständig oder nicht korrekt (alle Felder sind 'required')";

	@FXML
	private Label lblError;

	@FXML
	private TextField txtProduktName;

	@FXML
	private TextField txtProduktCode;

	@FXML
	private TextField txtProduktPreis;

	@FXML
	private TextField txtBeschreibung;

	@FXML
	private TextField txtSucheProduktCode;

	@FXML
	private ComboBox<KategorieTyp> cmbKategorie;

	@FXML
	private TableView<ProduktWrapper> tblProdukt;

	@FXML
	private Button btnLoeschen;

	@FXML
	private TableColumn<ProduktWrapper, String> colProduktName;

	@FXML
	private TableColumn<ProduktWrapper, Double> colPreis;

	@FXML
	private TableColumn<ProduktWrapper, String> colBeschreibung;

	@FXML
	private TableColumn<ProduktWrapper, String> colProduktCode;

	@FXML
	void zuruck (ActionEvent event) {
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

	public void initialize(URL location, ResourceBundle resources) {

		try {

			lblError.setText("");

			/* cmbKategorie initialisieren */
			List<KategorieTyp> kategorieListe = new ArrayList<>();

			KategorieTyp k1 = KategorieTyp.GETRANK;
			KategorieTyp k2 = KategorieTyp.SNACK;
			KategorieTyp k3 = KategorieTyp.SPEISE;

			kategorieListe.add(k1);
			kategorieListe.add(k2);
			kategorieListe.add(k3);
			
			cmbKategorie.getItems().addAll(kategorieListe);

			if (tblProdukt.getItems().size() > 0 && cmbKategorie.getItems().size() > 0) {
				cmbKategorie.getSelectionModel().select(0);
			}

			/* Tabelle konfigurieren */
			colProduktCode.setCellValueFactory(new PropertyValueFactory<ProduktWrapper, String>("produktCode"));
			colProduktName.setCellValueFactory(new PropertyValueFactory<ProduktWrapper, String>("name"));
			colPreis.setCellValueFactory(new PropertyValueFactory<ProduktWrapper, Double>("preis"));
			colBeschreibung.setCellValueFactory(new PropertyValueFactory<ProduktWrapper, String>("beschreibung"));

			tblProdukt.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProduktWrapper>() {

				@Override
				public void changed(ObservableValue<? extends ProduktWrapper> observable, ProduktWrapper oldValue,
									ProduktWrapper newValue) {
					if (newValue != null) {
						updateView();
					}
				}
			});

			updateTable();

			btnLoeschen.disableProperty()
					.bind(Bindings.size(tblProdukt.getSelectionModel().getSelectedItems()).isEqualTo(0));

		} catch (Exception e) {
			logger.error("Fehler bei der Initialisierung der View: ", e);
			return;
		}
	}

	@FXML
	private void suche() {

		String produktCode = txtSucheProduktCode.getText();
		Produkt p;

		try {

			p = Context.getInstance().getProduktService().findByProduktCode(produktCode);

			if (p == null) {
				txtSucheProduktCode.setText("Kein gültiger Code");
				tblProdukt.getItems().clear();

			} else {

				tblProdukt.getItems().clear();
				ProduktWrapper pWrapper = new ProduktWrapper(p);
				tblProdukt.getItems().add(pWrapper);

				txtSucheProduktCode.setText("");

			}
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Ein Fehler ist aufgetreten");
		}

	}

	@FXML
	private void updateTable() {

		lblError.setText("");

		try {

			List<Produkt> produktListe = Context.getInstance().getProduktService().alleProdukt();


			if (produktListe.size() > 0) {
				List<ProduktWrapper> wrapperListe = new ArrayList<>();


				for (Produkt produkt : produktListe) {
					wrapperListe.add(new ProduktWrapper(produkt));
				}

				tblProdukt.getItems().clear();
				tblProdukt.getItems().addAll(wrapperListe);

				tblProdukt.getSelectionModel().select(0);

				updateView();
			}

		} catch (Exception e) {
			logger.error("Fehler beim Update der Tabelle: ", e);
			throw new RuntimeException(e);
		}

	}

	private void updateView() {

		lblError.setText("");

		if (tblProdukt.getSelectionModel().getSelectedItem() == null) {

			cmbKategorie.getSelectionModel().clearSelection();
			txtProduktName.setText("");
			txtProduktPreis.setText("0.0");
			txtBeschreibung.setText("");
			txtProduktCode.setText("");

		} else {

			Produkt p = tblProdukt.getSelectionModel().getSelectedItem().getProdukt();

				cmbKategorie.getSelectionModel().select(p.getKategorie());
				txtProduktName.setText(p.getName());
				txtProduktCode.setText(p.getProduktCode());
				txtProduktPreis.setText("" + p.getPreis());
				txtBeschreibung.setText(p.getBeschreibung());
		}

	}

	@FXML
	private void neuesProduktErfassen() {
		reset();
		txtProduktName.requestFocus();
	}

	@FXML
	private void speichern() {

		if (eingabeValid()) {

			if (tblProdukt.getSelectionModel().getSelectedItem() == null) {

				/* Neuen Benutzer einfügen */
				String name = txtProduktName.getText();
				String produktCode = txtProduktCode.getText();
				double preis = Double.parseDouble(txtProduktPreis.getText());
				String beschreibung = txtBeschreibung.getText();
				KategorieTyp kategorie = cmbKategorie.getSelectionModel().getSelectedItem();

				Produkt produkt = new Produkt(produktCode, name, beschreibung, preis, kategorie);

				try {
					if(Context.getInstance().getProduktService().findByProduktCode(produkt.getProduktCode())==null) {
						Context.getInstance().getProduktService().produktHinzufuegen(produkt);
					} else {
						lblError.setText(ERROR_MSG_SPEICHERN_MISSLUNGEN);
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Produkt speichern");
						alert.setHeaderText("Information");
						alert.setContentText("Das Hinzufügen des neuen Produkts ist misslungen.");
						alert.showAndWait();
					}
				} catch (Exception e) {
					logger.error("Fehler beim Hinzufügen eines neuen Produkt: ", e);
				}
			} else {

				/* Den selektierten Benutzer updaten */
				String name = txtProduktName.getText();
				String produktCode = txtProduktCode.getText();
				double preis = Double.parseDouble(txtProduktPreis.getText());
				String beschreibung = txtBeschreibung.getText();
				KategorieTyp kategorie = cmbKategorie.getSelectionModel().getSelectedItem();

				Produkt produkt = tblProdukt.getSelectionModel().getSelectedItem().getProdukt();

				produkt.setProduktCode(produktCode);
				produkt.setName(name);
				produkt.setBeschreibung(beschreibung);
				produkt.setPreis(preis);
				produkt.setKategorie(kategorie);

				try {
					if (Context.getInstance().getProduktService().findByProduktCode(produkt.getProduktCode())==null) {
						Context.getInstance().getProduktService().produktAktualisieren(produkt);
					} else {
						lblError.setText(ERROR_MSG_UPDATE_MISSLUNGEN);
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Produkt speichern");
						alert.setHeaderText("Information");
						alert.setContentText("Das Aktualisieren des ausgewählten Produkts ist misslungen.");
						alert.showAndWait();
					}
				} catch (Exception e) {
					logger.error("Fehler beim Aktualisieren eines neuen Produkts: ", e);
				}
			}

			updateTable();
			reset();
			txtProduktName.requestFocus();
		}

	}

	private boolean eingabeValid() {

		lblError.setText("");
		if (isValid(txtProduktName.getText()) && isValid(txtProduktCode.getText()) && isValid(txtProduktPreis.getText())
				&& isValid(txtBeschreibung.getText())) {
			/* Prüfen, od der Preis korrekt eingegeben wurde */
			try {
				Double.parseDouble(txtProduktPreis.getText());
				return true;
			} catch (NumberFormatException e) {
				lblError.setText(ERROR_MSG_PREIS_EINGABE_NICHT_KORREKT);
				return false;
			}
		} else {
			lblError.setText(ERROR_MSG_EINGABE_NICHT_KORREKT);
			return false;
		}
	}

	private boolean isValid(String str) {
		return str != null && str.trim().length() > 0;
	}

	@FXML
	private void loeschen() {

		if (tblProdukt.getSelectionModel().getSelectedItem() == null) {
			return;
		}

		Produkt produkt = tblProdukt.getSelectionModel().getSelectedItem().getProdukt();

		if (produkt != null) {
			try {
				Context.getInstance().getProduktService().produktLoeschen(produkt);
				updateTable();
				updateView();
			} catch (Exception e) {
				logger.error("Fehler beim Löschen des Produkt: ", e);
				lblError.setText(ERROR_MSG_LOESCHEN_MISSLUNGEN);
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Produkt löschen");
				alert.setHeaderText("Information");
				alert.setContentText("Das Löschen des Produkt ist misslungen.");
				alert.showAndWait();
			}
		}
	}


	@FXML
	private void reset() {

		tblProdukt.getSelectionModel().clearSelection();
		txtProduktName.setText("");
		txtProduktCode.setText("");
		txtProduktPreis.setText("0.0");
		txtBeschreibung.setText("");
		tblProdukt.getSelectionModel().clearSelection();

	}

}

