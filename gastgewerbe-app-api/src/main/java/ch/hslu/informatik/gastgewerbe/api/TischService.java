package ch.hslu.informatik.gastgewerbe.api;


import ch.hslu.informatik.gastgewerbe.model.Tisch;

import java.util.List;

public interface TischService {

	//Fuegt Tisch hinzu
	Tisch tischHinzufuegen(Tisch tisch) throws Exception;

	//Aktualisiert Tisch hinzu
	Tisch tischAktualisieren(Tisch tisch) throws Exception;

	//Löscht Tisch
	void tischLoeschen(Tisch tisch) throws Exception;

	//Tisch nach Nummer suchen und finden
	Tisch findByTischNummer(int tischNr) throws Exception;
	
	//Zeigt alle Tische an
	List<Tisch> alleTische() throws Exception;

}
