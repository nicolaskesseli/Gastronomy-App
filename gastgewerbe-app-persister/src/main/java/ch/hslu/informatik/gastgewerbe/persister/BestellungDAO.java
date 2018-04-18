package ch.hslu.informatik.gastgewerbe.persister;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;

public interface BestellungDAO extends GenericPersisterDAO<Bestellung> {

	
	Bestellung findByProduktID(int ProduktID) throws Exception;
	
	
	
}
