package ch.hslu.informatik.gastgewerbe.gui.kueche;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;


public class MainWindowKuecheController {

	private static Logger logger = LogManager.getLogger(MainWindowKuecheController.class);
	
	@FXML
    private TableView<?> bestellübersichtBarTbl;

    @FXML
    private Button bestellungBereitBtn;

    @FXML
    private Button bestellungLöschenBtn;

   

   /* @FXML
    void bestellungBereit(ActionEvent event) {

    }

    @FXML
    void bestellungLöschen(ActionEvent event) {

    }*/
}
