package ch.hslu.informatik.gastgewerbe.gui.bar;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindowBarController {

	private static Logger logger = LogManager.getLogger(MainWindowBarController.class);
	
	@FXML
	private TableView<?> bestellübersichtBarTbl;
	
	@FXML 
	private Button bestellungBereitBtn;
	
	@FXML
	private Button bestellungLöschenBtn;
	
	@FXML
	private Button abmeldeBtn;
	
	@FXML
	void abmelden (ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
		//	stage.initModality(Modality.APPLICATION_MODAL);
		//	stage.initStyle(StageStyle.UNDECORATED);
		//	stage.setTitle("Bestellung erfassen");
			stage.setScene(new Scene(root1));
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
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
	
	
	
}
