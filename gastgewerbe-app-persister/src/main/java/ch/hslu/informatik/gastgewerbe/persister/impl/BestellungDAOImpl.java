package ch.hslu.informatik.gastgewerbe.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.persister.BestellungDAO;

public class BestellungDAOImpl extends GenericPersisterDAOImpl<Bestellung> implements BestellungDAO {
	
	private static final Logger logger = LogManager.getLogger(BestellungDAOImpl.class);
	
	public BestellungDAOImpl() {
		super(Bestellung.class);
	}

	@Override
	public Bestellung findByProduktID(int ProduktID) throws Exception {
		
		return null;
	}
	
}
