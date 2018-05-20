package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;

import javafx.scene.layout.AnchorPane;
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

public class BestellungBereitController {

    /* TODO @Andy Fenster FXML und Controller Bestellung bereit */
	
	private static Logger logger = LogManager.getLogger(BestellungBereitController.class);
	
    @FXML
    private TableView<?> bestellungBereitTabelle;

    @FXML
    private Button bestellpositionAbschliessenBtn;

    @FXML
    private Button zurückBtn;

    @FXML
    void bestellungAbschliessen(ActionEvent event) {

    }

    @FXML
    void zurück(ActionEvent event) {
        try {
            AnchorPane backRoot = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

            Scene backScene = new Scene(backRoot);

            Stage mainStage = Context.getInstance().getMainStage();

            mainStage.setScene(backScene);
            mainStage.show();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);

        }

    }

}
