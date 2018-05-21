package ch.hslu.informatik.gastgewerbe.gui.verwaltung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {

		Logger logger = LogManager.getLogger(MainApp.class);

		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));

			BorderPane mainRoot = new BorderPane();

			mainRoot.setMinWidth(1200);
			mainRoot.setMinHeight(600);

			mainRoot.setPrefWidth(1200);
			mainRoot.setPrefHeight(600);

			mainRoot.setMaxWidth(1200);
			mainRoot.setMaxHeight(600);

			/* mainRoot 'exportieren' zu Context */
			Context.getInstance().setMainRoot(mainRoot);

			Scene scene = new Scene(mainRoot);
			mainRoot.setCenter(root);

			scene.getStylesheets().add("/styles/Styles.css");

			primaryStage.setTitle("Gastgewerbe-Verwaltung");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e){
			logger.error("Fehler bei der Mainklasse");
		}
	}

	// Beendet automatische aktualisierung Tabelle Bestellungen übersicht

	@Override
	public void stop() throws Exception {
		super.stop();
		System.exit(0);
	}


	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 *
	 * @param args
	 *            the command line arguments
	 */
		public static void main(String[] args) {
			launch(args);
		}

	}


