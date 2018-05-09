package ch.hslu.informatik.gastgewerbe.gui.bar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
			Scene scene = new Scene(root,850,570);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

