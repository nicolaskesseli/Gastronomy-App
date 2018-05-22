package ch.hslu.informatik.gastgewerbe.persister;


import ch.hslu.informatik.gastgewerbe.model.Credentials;

/**
 * Interface für Persistierung von Credentials-Entities.
 * 
 * @version 1.0
 * @author jsucur
 * 
 */
public interface CredentialsDAO extends GenericPersisterDAO<Credentials> {
    /**
     * Liefert die Credentials für den übergebenen Benutzernamen zurück.
     * 
     * @param benutzername
     * @return
     * @throws Exception
     */
    Credentials findByBenutzername(String benutzername) throws Exception;
}

