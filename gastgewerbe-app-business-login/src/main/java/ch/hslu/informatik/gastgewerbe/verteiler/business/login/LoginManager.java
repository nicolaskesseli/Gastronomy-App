package ch.hslu.informatik.gastgewerbe.verteiler.business.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.api.LoginService;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.Credentials;
import ch.hslu.informatik.gastgewerbe.persister.BenutzerDAO;
import ch.hslu.informatik.gastgewerbe.persister.CredentialsDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BenutzerDAOImpl;
import ch.hslu.informatik.gastgewerbe.persister.impl.CredentialsDAOImpl;

public class LoginManager implements LoginService {

	private static Logger logger = LogManager.getLogger(LoginManager.class);

	private BenutzerDAO benutzerDAO;
	private CredentialsDAO credentialsDAO;

	// Liefert einen BenutzerDAOImpl zurück
	public BenutzerDAO getBenutzerDAO() {

		if (benutzerDAO == null) {
			benutzerDAO = new BenutzerDAOImpl();
		}

		return benutzerDAO;
	}
	// Liefert einen CredentialsDAOImpl zurück
	public CredentialsDAO getCredentialsDAO() {

		if (credentialsDAO == null) {
			credentialsDAO = new CredentialsDAOImpl();
		}

		return credentialsDAO;
	}

    /*Methode um sich einzuloggen*/
	public Benutzer login(String benutzername, String kennwort) throws Exception {

		try {

			Benutzer b = getBenutzerDAO().findByBenutzername(benutzername);

			if (b != null) {
				return kennwort.equals(b.getCredentials().getPasswort()) ? b : null;
			}

			return null;

		} catch (Exception e) {
			String msg = "Benutzer \'" + benutzername + "\' konnte nicht angemeldet werden";
			logger.error(msg, e);
			throw new Exception(msg + e);
		}
	}

	//Methode, um das Kennwort zu ändern
	public boolean kennwortAendern(String benutzername, String kennwortAktuell, String kennwortNeu) throws Exception {

		try {
			Benutzer b = getBenutzerDAO().findByBenutzername(benutzername);

			if (kennwortAktuell.equals(b.getCredentials().getPasswort())) {
				Credentials c = b.getCredentials();
				c.setPasswort(kennwortNeu);
				/* Updaten */
				c = getCredentialsDAO().save(c);

				if (kennwortNeu.equals(c.getPasswort())) {
					return true;
				}

				return false;
			}
		} catch (Exception e) {
			String msg = "Kennwort des Benutzers \'" + benutzername + "\' konnte nicht geändert werden";
			logger.error(msg, e);
			throw new Exception(msg + e);
		}

		return false;
	}

}

