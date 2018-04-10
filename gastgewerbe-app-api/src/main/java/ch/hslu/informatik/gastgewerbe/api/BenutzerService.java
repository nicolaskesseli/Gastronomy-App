package ch.hslu.informatik.gastgewerbe.api;

import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.RolleTyp;

public interface BenutzerService {

	//Fuegt Benutzer hinzu
	Benutzer benutzerHinzufuegen(Benutzer benutzer) throws Exception;
	
	
	//Bestehenden Benutzer ändern
	Benutzer benutzerAktualisieren(Benutzer benutzer) throws Exception;
	
	
	//Löscht Benutzer	
	void benutzerLoeschen(Benutzer benutzer) throws Exception;
	
	
	//Benutzer nach Name suchen und finden
	Benutzer findByBenutzername(String benutzername) throws Exception;
	
	
	//Zeigt alle Benutzer mit bestimmten Typ an
	List<Benutzer> findByRolleTyp(RolleTyp rolleTyp) throws Exception;
	
	
	//Zeigt alle Benutzer mit gegebenen Namen
	List<Benutzer> findByNachnameUndVorname(String nachname, String vorname) throws Exception;
	
	
	//Zeigt alle Benutzer an
	List<Benutzer> alleBenutzer() throws Exception;
	
	
	//Zeigt alle Rollen an (Admin, User)
	List<RolleTyp> alleRollen() throws Exception;
	
}
