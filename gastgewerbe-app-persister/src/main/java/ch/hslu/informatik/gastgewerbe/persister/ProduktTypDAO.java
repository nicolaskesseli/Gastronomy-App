package ch.hslu.informatik.gastgewerbe.persister;


import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.Produkt;

/**
 * Interface für Persistierung von ProduktTyp-Entities.
 * 
 * @version 1.0
 * @author jsucur
 * 
 */
public interface ProduktTypDAO extends GenericPersisterDAO<Produkt> {

    /**
     * Liefert alle ProduktTypen für den übergebenen Namen zurück.
     * 
     * @param name
     * @return
     * @throws Exception
     */
    List<Produkt> findByName(String name) throws Exception;

    /**
     * Liefert den ProduktTyp für den übergebenen Typ-Code zurück.
     * 
     * @param typCode
     * @return
     * @throws Exception
     */
    Produkt findByTypCode(String typCode) throws Exception;

    
}
