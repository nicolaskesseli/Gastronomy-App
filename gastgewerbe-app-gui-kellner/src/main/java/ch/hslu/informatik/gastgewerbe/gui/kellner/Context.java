package ch.hslu.informatik.gastgewerbe.gui.kellner;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.api.BenutzerService;
import ch.hslu.informatik.gastgewerbe.api.LoginService;
import ch.hslu.informatik.gastgewerbe.businessbenutzer.BenutzerManager;
import ch.hslu.informatik.gastgewerbe.businessprodukt.ProduktManager;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.verteiler.business.login.LoginManager;
import javafx.scene.layout.BorderPane;

public class Context {

	private static Logger logger = LogManager.getLogger(Context.class);

	private static final String PROPERTY_FILE_NAME = "client.properties";

	private static Context INSTANCE = new Context();

	private BorderPane mainRoot;

	private Benutzer benutzer;

	private BenutzerService benutzerService;

	private LoginService loginService;
	
	private ProduktManager produktManager;

	private Context() {

	}

	public static Context getInstance() {
		return INSTANCE;
	}

	public BorderPane getMainRoot() {
		return mainRoot;
	}

	public void setMainRoot(BorderPane mainRoot) {
		this.mainRoot = mainRoot;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}



	public BenutzerService getBenutzerService() {

		if (benutzerService == null) {
			benutzerService = new BenutzerManager();
		}

		return benutzerService;
	}

	public LoginService getLoginService() {

		if (loginService == null) {
			loginService = new LoginManager();
		}

		return loginService;
	}
	
	public ProduktManager getProduktManager() {
		if (produktManager == null) {
			produktManager = new ProduktManager();
		}
		
		return produktManager;
	}

	

}

