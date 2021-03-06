package ch.hslu.informatik.gastgewerbe.gui.kellner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainApp extends Application {
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		
		Logger logger = LogManager.getLogger(MainApp.class);

		
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
			Scene scene = new Scene(root,850,570);
			
			Context.getInstance().setMainStage(primaryStage);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
	}

	// Beendet automatische aktualisierung Tabelle Bestellungen übersicht

	@Override
	public void stop() throws Exception {
		super.stop();
		System.exit(0);
	}

	public static void main(String[] args) {
	launch(args);
		
	}
	
	
}

