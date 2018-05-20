package ch.hslu.informatik.gastgewerbe.gui.kellner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class BestellungBearbeitenController {

    private static Logger logger = LogManager.getLogger(BestellungBearbeitenController.class);

    @FXML
    void back(ActionEvent event) {
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
