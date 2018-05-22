package ch.hslu.informatik.rmi.impl;

import ch.hslu.informatik.gastgewerbe.api.BenutzerService;
import ch.hslu.informatik.gastgewerbe.businessbenutzer.BenutzerManager;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.RolleTyp;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiBenutzerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

@SuppressWarnings("serial")
public class RmiBenutzerServiceImpl extends UnicastRemoteObject implements RmiBenutzerService {

	private BenutzerService benutzerService;

	public RmiBenutzerServiceImpl() throws RemoteException {

	}

	public BenutzerService getBenutzerService() {
		if (benutzerService == null) {
			benutzerService = new BenutzerManager();

		}
		return benutzerService;
	}

	@Override
	public Benutzer benutzerHinzufuegen(Benutzer benutzer) throws Exception {
		return getBenutzerService().benutzerHinzufuegen(benutzer);
	}

	@Override
	public Benutzer benutzerAktualisieren(Benutzer benutzer) throws Exception {
		return getBenutzerService().benutzerAktualisieren(benutzer);
	}

	@Override
	public void benutzerLoeschen(Benutzer benutzer) throws Exception {
		getBenutzerService().benutzerLoeschen(benutzer);

	}

	@Override
	public Benutzer findByBenutzername(String benutzername) throws Exception {
		return getBenutzerService().findByBenutzername(benutzername);
	}

	@Override
	public List<Benutzer> findByRolleTyp(RolleTyp rolleTyp) throws Exception {
		return getBenutzerService().findByRolleTyp(rolleTyp);
	}

	@Override
	public List<Benutzer> findByNachnameUndVorname(String nachname, String vorname) throws Exception {
		return getBenutzerService().findByNachnameUndVorname(nachname, vorname);
	}

	@Override
	public List<Benutzer> alleBenutzer() throws Exception {
		return getBenutzerService().alleBenutzer();
	}

	@Override
	public List<RolleTyp> alleRollen() throws Exception {
		return getBenutzerService().alleRollen();
	}
}
