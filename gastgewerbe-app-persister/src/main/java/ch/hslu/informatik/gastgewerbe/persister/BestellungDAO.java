package ch.hslu.informatik.gastgewerbe.persister;

import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;

public interface BestellungDAO extends GenericPersisterDAO<Bestellung> {
	
	
	
	
	
	List<Bestellung> findByTischId(double TischId) throws Exception;
	
	
	
	
	

}
