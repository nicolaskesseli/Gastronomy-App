package ch.hslu.informatik.gastgewerbe.persister;

import java.time.LocalDate;
import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;

public interface BestellungDAO extends GenericPersisterDAO<Bestellung> {
	
	
	
	
	
	List<Bestellung> findByTischId(int TischId) throws Exception;

	List<Bestellung> findByZeit(LocalDate zeit) throws Exception;
	
	
	
	

}
