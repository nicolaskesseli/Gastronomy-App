package ch.hslu.informatik.gastgewerbe.gui.bar;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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
	private TableColumn<BestellungPosition, String> colProdukt;

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
	    }

    @FXML
	    void bestellungLöschen(ActionEvent event) {

	    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

	    try{

	        bestellungen.addAll(Context.getInstance().getBestellungService().alleBestellungen());

	        colTischNr.setCellValueFactory(new PropertyValueFactory<Bestellung,Integer>("tischNr"));



        }catch (Exception e) {
            logger.error("Fehler bei der View-Initialisierung: ", e);
            throw new RuntimeException(e);
        }

    }

    private void updateTable() {
        bestellübersichtBarTbl.getItems().clear();
        bestellübersichtBarTbl.getItems().addAll(bestellungen);
        bestellübersichtBarTbl.getSelectionModel().select(0);
    }
}
