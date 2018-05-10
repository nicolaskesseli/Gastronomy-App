package ch.hslu.informatik.gastgewerbe.gui.kellner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainApp extends Application {
	
	
	public static void main(String[] args) {
		Application.launch(MainApp.class, (java.lang.String[]) null);
	}
	
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
	
	
}

