package ch.hslu.informatik.gastgewerbe.persister;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;

public interface BestellungDAO extends GenericPersisterDAO<Bestellung> {

	
	Bestellung findByProduktId(int ProduktId) throws Exception;
	
	
	
	Bestellung findByProduktBezeichnung(String bezeichnung) throws Exception;
	
	
	
	Bestellung findByProduktKategorie(String kategorie) throws Exception;
	
	
	
}
