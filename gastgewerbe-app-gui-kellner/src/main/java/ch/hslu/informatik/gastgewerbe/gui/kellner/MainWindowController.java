package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

	

	import javafx.fxml.FXML;
	import javafx.scene.control.Button;

	public class MainWindowController {
		
		private static Logger logger = LogManager.getLogger(MainWindowController.class);

	    @FXML
	    private Button tischAbrechnenBtn;

	    @FXML
	    private Button bestellungErfassen;

	    @FXML
	    private Button offeneBestellungen;

	    @FXML
	    private Button abmeldeBtn;

	    @FXML
	    void bestellungErfassen(ActionEvent event) {
	    	try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BestellungErfassenView.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setTitle("Bestellung erfassen");
				stage.setScene(new Scene(root1));
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);

			}
	    }

	    @FXML
	    void offeneBestellungen(ActionEvent event) {
	    	try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BestellungBereitView.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setTitle("Offene Bestellungen");
				stage.setScene(new Scene(root1));
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
	    	} catch (IOException e) {
				logger.error(e.getMessage(), e);

			}
	    }

	    @FXML
	    void tischAbrechnen(ActionEvent event) {
	    	try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AbrechnungTischView.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setTitle("Tisch abrechnen");
				stage.setScene(new Scene(root1));
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
	    	} catch (IOException e) {
				logger.error(e.getMessage(), e);

			}
	    }
	    
	    @FXML
		private void abmelden(ActionEvent event) {

			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root1));
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);

			}

	}


	

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}