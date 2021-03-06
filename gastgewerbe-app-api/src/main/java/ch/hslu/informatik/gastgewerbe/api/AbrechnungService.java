package ch.hslu.informatik.gastgewerbe.api;

import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.Tisch;


import java.time.LocalDateTime;
import java.util.List;

public interface AbrechnungService {


	
	//Rechnet Tisch ab und liefert zu bezahlenden Betrag
	double tischAbrechnen(Tisch tisch, Benutzer benutzer, Bestellung bestellung) throws Exception;

	// Abschluss aller Bestellungen für Tagesabrechnung
	double abschluss(List<Abrechnung> abrechnungen) throws Exception;

	// Liefert alle Abnrechnung für den übergeben Benutzer und Datum zurück
	List<Abrechnung> findByBenutzerUndDatum(Benutzer benutzer, LocalDateTime zeit) throws Exception;

    // Lieferte alle Abrechnungen für das übergebene LocalDateTime
    List<Abrechnung> findByDatum(LocalDateTime zeit) throws Exception;

    // Gibt Abrechnung mit der übergebenen ID zurück
	Abrechnung findById(Long id) throws Exception;

	// Liefert alle Abrechnungen
	List<Abrechnung> alleAbrechnungen() throws Exception;

}
