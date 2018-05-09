	package ch.hslu.informatik.gastgewerbe.gui.kellner;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

	public class BestellungErfassenController {

		private static Logger logger = LogManager.getLogger(BestellungErfassenController.class);
		
	    @FXML
	    private TextField tischNrInput;

	    @FXML
	    private TableView<?> bestellübersichtTbl;

	    @FXML
	    private TextField gerichtNrInput;

	    @FXML
	    private Button bestellungAbschickenBtn;

	    @FXML
	    private TextField bemerkungInput;

	    @FXML
	    private Button zurückInput;

	    @FXML
	    private TableView<?> gerichtDetailsTbl;

	    @FXML
	    private Button gerichtHinzufügenBtn;

	    @FXML
	    private Button suchenGerichtBtn;

	    @FXML
	    void bestellungAbschicken(ActionEvent event) {

	    }

	    @FXML
	    void bestellungHinzufügen(ActionEvent event) {

	    }

	    @FXML
	    void gerichtSuchen(ActionEvent event) {



	    }

	    @FXML
	    void zuruck(ActionEvent event) {
	    	try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
			//	stage.initModality(Modality.APPLICATION_MODAL);
			//	stage.initStyle(StageStyle.UNDECORATED);
			//	stage.setTitle("Hauptseite");
				stage.setScene(new Scene(root1));
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
	    	} catch (IOException e) {
				logger.error(e.getMessage(), e);

			}
	    }

	}

	

