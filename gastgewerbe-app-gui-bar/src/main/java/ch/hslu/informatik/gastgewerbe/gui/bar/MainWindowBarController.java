package ch.hslu.informatik.gastgewerbe.gui.bar;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungPositionWrapper;
import ch.hslu.informatik.gastgewerbe.gui.wrapper.BestellungWrapper;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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

public class MainWindowBarController extends TimerTask implements Initializable {

	private static Logger logger = LogManager.getLogger(MainWindowBarController.class);

	private List<BestellungWrapper> bestellungenListe = new ArrayList<>();

	private List<BestellungPositionWrapper> positionenListe = new ArrayList<>();

	@FXML
	private Button bestellungBereitBtn;

	@FXML
	private Button bestellungLöschenBtn;

	@FXML
	private TableView<BestellungWrapper> tblBestBar;

	@FXML
	private TableColumn<BestellungWrapper, Integer> colTischNr;

	@FXML
	private TableColumn<BestellungWrapper, LocalDateTime> colZeit;

	@FXML
	private TableColumn<BestellungWrapper, String> colBemerkung;

	@FXML
	private TableView<BestellungPositionWrapper> tblPosBar;

	@FXML
	private TableColumn<BestellungPositionWrapper, Long> colPos;

	@FXML
	private TableColumn<BestellungPositionWrapper, Integer> colAnzahl;

	@FXML
	private TableColumn<BestellungPositionWrapper, String> colProdukt;

	@FXML
	private Label lblBenutzer;

	@FXML
	void abmelden(ActionEvent event) {
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

		try {
			ausgewähltePosition = tblPosBar.getSelectionModel().getSelectedItem();

			if (ausgewähltePosition != null) {
				BestellungPosition bereit = Context.getInstance().getBestellungService()
						.bestPosFindById(ausgewähltePosition.getId());
				Context.getInstance().getBestellungService().bestellungPositionBereit(bereit);
				tblPosBar.getItems().remove(ausgewähltePosition);
			}

			updateTable();

		} catch (Exception e) {
			String msg = "BestellPos. konnte nicht in Status bereit versetzt werden!";
			logger.error(msg, e);

			// error dialog anzeigen
			Alert error = new Alert(Alert.AlertType.ERROR);
			error.setTitle("Fehler");
			error.setHeaderText("Fehler bei Statusänderung");
			error.setContentText(
					"Die ausgewählte Position konnte nicht in den Status bereit versetzt werden. Bitte wenden Sie sich an Ihren Systemadministrator.");
			error.showAndWait();
		}
	}

	@FXML
	void bestellungLöschen(ActionEvent event) {

		BestellungWrapper ausgewahlteBestellung;

		try {
			ausgewahlteBestellung = tblBestBar.getSelectionModel().getSelectedItem();

			if (ausgewahlteBestellung != null) {
				Bestellung löschen = Context.getInstance().getBestellungService()
						.findById(ausgewahlteBestellung.getId());
				Context.getInstance().getBestellungService().deleteBestellung(löschen);
			}

			tblPosBar.getItems().clear();
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
			error.setContentText(
					"Die ausgewählte Bestellung konnte nicht gelöscht werden. Bitte wenden Sie sich an Ihren Systemadministrator.");
			error.showAndWait();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		String str = Context.getInstance().getBenutzer().getVorname() + " "
				+ Context.getInstance().getBenutzer().getNachname();
		lblBenutzer.setText("Angemeldet: " + str);
		lblBenutzer.setAlignment(Pos.BASELINE_CENTER);

		// Zeit formatieren

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");

		try {

			// Tabelle initialisieren
			colTischNr.setCellValueFactory(new PropertyValueFactory<BestellungWrapper, Integer>("tischNr"));
			colZeit.setCellValueFactory(new PropertyValueFactory<BestellungWrapper, LocalDateTime>("zeit"));
			colBemerkung.setCellValueFactory(new PropertyValueFactory<BestellungWrapper, String>("bemerkung"));

			colPos.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Long>("id"));
			colAnzahl.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, Integer>("anzahl"));
			colProdukt.setCellValueFactory(new PropertyValueFactory<BestellungPositionWrapper, String>("produkt"));

			// Datumformat anpassen CellFactory anpassen um nach dateFormatter zu
			// formatieren

			colZeit.setCellFactory(
					new Callback<TableColumn<BestellungWrapper, LocalDateTime>, TableCell<BestellungWrapper, LocalDateTime>>() {

						@Override
						public TableCell<BestellungWrapper, LocalDateTime> call(
								TableColumn<BestellungWrapper, LocalDateTime> param) {

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

			/* Auf Click werden die Positionen der ausgewählten Bestellung angezeigt */
			tblBestBar.setRowFactory(new Callback<TableView<BestellungWrapper>, TableRow<BestellungWrapper>>() {

				@Override
				public TableRow<BestellungWrapper> call(TableView<BestellungWrapper> param) {
					TableRow<BestellungWrapper> tRow = new TableRow<>();

					tRow.setOnMouseClicked(event -> {
						if (event.getClickCount() == 1 && !tRow.isEmpty()) {
							BestellungWrapper item = tRow.getItem();

							Bestellung bestellung = item.getBestellung();

							if (bestellung != null) {

								tblPosBar.getItems().clear();
								positionenListe.clear();

								List<BestellungPosition> tempListe = bestellung.getBestellungPositionListe();
								for (BestellungPosition p : tempListe) {
									if (p.getProdukt().getKategorie().equals(KategorieTyp.SNACK)
											|| p.getProdukt().getKategorie().equals(KategorieTyp.GETRANK)) {
										if (!p.isBestellungBereit()) {
											positionenListe.add(new BestellungPositionWrapper(p));
										}
									}
								}
								tblPosBar.getItems().addAll(positionenListe);
								tblPosBar.getSelectionModel().select(0);
							}
						}
					});

					return tRow;
				}
			});

			// Tabelle aktualisieren
			updateTable();

			/* Binding für die Schaltfläche 'Löschen' erstellen */
			bestellungLöschenBtn.disableProperty().bind(Bindings.size(tblBestBar.getItems()).lessThan(1));
			/* Binding für die Schaltfläche 'Bereit' erstellen */
			bestellungBereitBtn.disableProperty().bind(Bindings.size(tblPosBar.getItems()).lessThan(1));

			// Automatisches aktualisieren
			MainWindowBarController task = new MainWindowBarController();
			Timer timer = new Timer();
			timer.schedule(task, 120000, 3000000);

		} catch (Exception e) {
			logger.error("Fehler bei der View-Initialisierung: ", e);
			throw new RuntimeException(e);
		}

	}

	private void updateTable() {

		tblBestBar.getItems().clear();
		bestellungenListe.clear();

		try {
			// Nur Bestellungen für Bar einlesen D.h. Kategorie SNACK GETRÄNK die noch nicht
			// bereit sind
			List<Bestellung> alleBestellungen = Context.getInstance().getBestellungService().findByBereit(false);

			List<Bestellung> barBestellungen = new ArrayList<>();

			for (Bestellung b : alleBestellungen) {
				List<BestellungPosition> bestellungPosition = b.getBestellungPositionListe();
				for (BestellungPosition p : bestellungPosition) {
					if (p.getProdukt().getKategorie().equals(KategorieTyp.SNACK)
							|| p.getProdukt().getKategorie().equals(KategorieTyp.GETRANK)) {
						if (!p.isBestellungBereit()) {
							if (!barBestellungen.contains(b)) {
								barBestellungen.add(b);
							}
						}
					}
				}
			}
			for (Bestellung b : barBestellungen) {
				bestellungenListe.add(new BestellungWrapper(b));
			}

			tblBestBar.getItems().addAll(bestellungenListe);
			tblBestBar.getSelectionModel().select(0);

		} catch (Exception e) {
			logger.error("Fehler beim updaten der Tabelle: ", e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void run() {
		try {

			updateTable();

		} catch (Exception e) {
			logger.info("Tabelle Update fehlgeschlagen. Grund: keine nicht bereiten Bestellungen" + e);
		}
	}
}
