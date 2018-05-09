package ch.hslu.informatik.gastgewerbe.gui.verwaltung;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProduktVerwaltenViewController {

	private static Logger logger = LogManager.getLogger(ProduktVerwaltenViewController.class);

	@FXML
	private TextField bezeichnungInput;

	@FXML
	private TextField nummerInput;

	@FXML
	private TextField preisInput;

	@FXML
	private ComboBox<?> kategorieCmb;

	@FXML
	private TextField txtBeschreibung;

	@FXML
	private Button hinzufuegenBtn;

	@FXML
	private Button resetBtn;

	@FXML
	private TableView<?> produktTbl;

	@FXML
	private TableColumn<?, ?> colNummer;

	@FXML
	private TableColumn<?, ?> colLieferant;

	@FXML
	private TableColumn<?, ?> colProduktTypCode;

	@FXML
	private TableColumn<?, ?> colPreis;

	@FXML
	private Button loeschenBtn;

	@FXML
	private TextField nummerLöschenInput;

	@FXML
	private Button suchenBtn;

	@FXML
	void hinzufügen(ActionEvent event) {

	}

	@FXML
	void loeschen(ActionEvent event) {

	}

	@FXML
	void reset(ActionEvent event) {

	}

	@FXML
	void suchen(ActionEvent event) {

	}

	@FXML
	void zurück(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindowVerwaltung.fxml"));
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
