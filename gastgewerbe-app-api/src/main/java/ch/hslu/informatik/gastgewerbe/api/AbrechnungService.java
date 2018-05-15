package ch.hslu.informatik.gastgewerbe.api;

import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.Tisch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AbrechnungService {


	
	//Rechnet Tisch ab und liefert zu bezahlenden Betrag
	double tischAbrechnen(Tisch tisch, Benutzer benutzer) throws Exception;

	// Abschluss aller Bestellungen für Tagesabrechnung
	double abschluss(LocalDateTime zeit) throws Exception;

	// Liefert alle rechnungen für den übergeben Benutzer und Datum zurück
	List<Abrechnung> findByBenutzerUndDatum(Benutzer benutzer, LocalDateTime zeit) throws Exception;


}
