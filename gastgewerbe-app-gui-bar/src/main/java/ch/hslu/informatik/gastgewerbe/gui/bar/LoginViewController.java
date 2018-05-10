package ch.hslu.informatik.gastgewerbe.gui.bar;


import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.api.LoginService;
import ch.hslu.informatik.gastgewerbe.gui.bar.Context;
import ch.hslu.informatik.gastgewerbe.gui.bar.LoginViewController;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.RolleTyp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {

	
	private static Logger logger = LogManager.getLogger(LoginViewController.class);

	public static String LOGIN_ERROR_MESSAGE = "Benutzername oder Kennwort nicht korrekt!";
	public static String ACCESS_DENIED_MESSAGE = "Sie haben keine Zugriffsberechtigung!";
	public static String SERVER_NOT_AVAILABLE = "Die Anmeldung ist zurzeit nicht möglich!";

	@FXML
	private Label lblError;

	@FXML
	private TextField txtBenutzername;

	@FXML
	private TextField txtKennwort;

	@FXML
	private Button button;

	public LoginViewController() {

	}

	@FXML
	private void anmelden(ActionEvent event) {

		lblError.setText("");

		LoginService loginService = null;

		try {
			loginService = Context.getInstance().getLoginService();
		} catch (Exception conException) {
			logger.error(conException.getMessage(), conException);
			lblError.setText(LoginViewController.SERVER_NOT_AVAILABLE);
			return;
		}

		try {

			Benutzer benutzer = loginService.login(txtBenutzername.getText(), txtKennwort.getText());

			if (benutzer != null) {

				if (benutzer.getRolle() == RolleTyp.BAR_MITARBEITER) {
					Context.getInstance().setBenutzer(benutzer);
					
					AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
					Context.getInstance().getMainRoot().setCenter(root);

				} else{
					lblError.setText(LoginViewController.ACCESS_DENIED_MESSAGE);
				}
				
			} else {
				lblError.setText(LoginViewController.LOGIN_ERROR_MESSAGE);
			}

		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		txtBenutzername.requestFocus();
	}

}
