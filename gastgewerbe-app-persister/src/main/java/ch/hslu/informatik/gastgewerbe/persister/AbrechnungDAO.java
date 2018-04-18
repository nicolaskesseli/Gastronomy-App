package ch.hslu.informatik.gastgewerbe.persister;

import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.Abrechnung;

public interface AbrechnungDAO extends GenericPersisterDAO<Abrechnung> {
	
	
	
	
	
	List<Abrechnung> findByTischID(double TischId) throws Exception;
	
	
	
	
	

}
