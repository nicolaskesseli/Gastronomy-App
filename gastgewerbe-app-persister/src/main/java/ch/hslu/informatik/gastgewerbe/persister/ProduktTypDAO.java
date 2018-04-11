package ch.hslu.informatik.gastgewerbe.persister;


import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.ProduktTyp;

/**
 * Interface für Persistierung von ProduktTyp-Entities.
 * 
 * @version 1.0
 * @author jsucur
 * 
 */
public interface ProduktTypDAO extends GenericPersisterDAO<ProduktTyp> {

    /**
     * Liefert alle ProduktTypen für den übergebenen Namen zurück.
     * 
     * @param name
     * @return
     * @throws Exception
     */
    List<ProduktTyp> findByName(String name) throws Exception;

    /**
     * Liefert den ProduktTyp für den übergebenen Typ-Code zurück.
     * 
     * @param typCode
     * @return
     * @throws Exception
     */
    ProduktTyp findByTypCode(String typCode) throws Exception;

    
}
