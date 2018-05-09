package ch.hslu.informatik.gastgewerbe.api;

import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.Tisch;

import java.util.Date;
import java.util.List;

public interface AbrechnungService {


	
	//Rechnet Tisch ab und liefert zu bezahlenden Betrag
	double tischAbrechnen(Tisch tisch) throws Exception;

	// Abschluss aller Bestellungen
	double abschluss() throws Exception;

	// Liefert alle rechnungen für den übergeben Benutzer und Datum zurück
	List<Abrechnung> findByBenutzerUndDatum(Benutzer benutzer, Date datum) throws Exception;


}
