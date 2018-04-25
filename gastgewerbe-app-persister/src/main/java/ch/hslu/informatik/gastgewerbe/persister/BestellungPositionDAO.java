package ch.hslu.informatik.gastgewerbe.persister;

import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.model.Produkt;

import java.util.List;

public interface BestellungPositionDAO extends GenericPersisterDAO<BestellungPosition>{

    /*
     * Liefert alle BestellungPosition-Objekte für das übergebene Produkt
     * zurück, falls welche vorhanden, sonst eine leere Liste.
     *

     */
    List<BestellungPosition> findByProdukt(Produkt produkt) throws Exception;
}
