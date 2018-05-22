package ch.hslu.informatik.gastgewerbe.businessbenutzer;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.api.BenutzerService;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.RolleTyp;
import ch.hslu.informatik.gastgewerbe.persister.BenutzerDAO;
import ch.hslu.informatik.gastgewerbe.persister.impl.BenutzerDAOImpl;

public class BenutzerManager implements BenutzerService {
	
	
	
	
	private static Logger logger = LogManager.getLogger(BenutzerManager.class);

    private BenutzerDAO benutzerDAO;

    public BenutzerDAO getBenutzerDAO() {

        if (benutzerDAO == null) {
            benutzerDAO = new BenutzerDAOImpl();
        }

        return benutzerDAO;
    }

    public Benutzer benutzerHinzufuegen(Benutzer benutzer) throws Exception {
        try {
            return getBenutzerDAO().save(benutzer);
        } catch (Exception e) {
            String msg = "Benutzer \'" + benutzer.getNachname() + " " + benutzer.getVorname()
                    + "\' konnte nicht hinzugefügt werden";
            logger.error(msg, e);
            throw new Exception(msg + e);
        }
    }

    public Benutzer benutzerAktualisieren(Benutzer benutzer) throws Exception {
        try {
            return getBenutzerDAO().update(benutzer);
        } catch (Exception e) {
            String msg = "Benutzer \'" + benutzer.getNachname() + " " + benutzer.getVorname()
                    + "\' konnte nicht aktualisiert werden";
            logger.error(msg, e);
            throw new Exception(msg + e);
        }
    }

    public void benutzerLoeschen(Benutzer benutzer) throws Exception {
        try {
            getBenutzerDAO().delete(benutzer);
        } catch (Exception e) {
            String msg = "Benutzer \'" + benutzer.getNachname() + " " + benutzer.getVorname()
                    + "\' konnte nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg + e);
        }
    }

    public Benutzer findByBenutzername(String benutzername) throws Exception {
        try {
            return getBenutzerDAO().findByBenutzername(benutzername);
        } catch (Exception e) {
            String msg = "Benutzer \'" + benutzername + "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg + e);
        }
    }

    public List<Benutzer> findByRolleTyp(RolleTyp rolleTyp) throws Exception {
        try {
            return getBenutzerDAO().findByRolleTyp(rolleTyp);
        } catch (Exception e) {
            String msg = "Benutzer als \'" + rolleTyp.bezeichnung() + "\' konnten nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg + e);
        }
    }

    public List<Benutzer> findByNachnameUndVorname(String nachname, String vorname) throws Exception {
        try {
            return getBenutzerDAO().findByNachnameUndVorname(nachname, vorname);
        } catch (Exception e) {
            String msg = "Benutzer \'" + nachname + " " + vorname + "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg + e);
        }
    }

    public List<Benutzer> alleBenutzer() throws Exception {
        try {
            return getBenutzerDAO().findAll();
        } catch (Exception e) {
            String msg = "Benutzer konnten nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg + e);
        }
    }

    public List<RolleTyp> alleRollen() throws Exception {

        List<RolleTyp> liste = new ArrayList<RolleTyp>();

        liste.add(RolleTyp.KELLNER);
        liste.add(RolleTyp.ADMINISTRATOR);
        liste.add(RolleTyp.BAR_MITARBEITER);
        liste.add(RolleTyp.KUECHE_MITARBEITER);

        return liste;
    }

}


