package ch.hslu.informatik.gastgewerbe.api;

public interface AbrechnungService {


	
	//Rechnet Tisch ab und liefert zu bezahlenden Betrag
	double tischAbrechnen(Tisch tisch) throws Exception;
	
	
	
	
	
	
}
