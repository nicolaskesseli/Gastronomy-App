package ch.hslu.informatik.gastgewerbe.api;

import ch.hslu.informatik.gastgewerbe.model.Tisch;

public interface AbrechnungService {


	
	//Rechnet Tisch ab und liefert zu bezahlenden Betrag
	double tischAbrechnen(Tisch tisch) throws Exception;
	
	
	
	
	
	
}
