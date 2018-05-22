package ch.hslu.informatik.gastgewerbe.persister;

import ch.hslu.informatik.gastgewerbe.model.Tisch;

public interface TischDAO extends GenericPersisterDAO<Tisch> {

	Tisch findByTischNr(int tischNr) throws Exception;
}
