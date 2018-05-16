package ch.hslu.informatik.gastgewerbe.gui.kueche;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {

        Logger logger = LogManager.getLogger(MainApp.class);

        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
            Scene scene = new Scene(root,900, 600);

            Context.getInstance().setMainStage(primaryStage);

            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}