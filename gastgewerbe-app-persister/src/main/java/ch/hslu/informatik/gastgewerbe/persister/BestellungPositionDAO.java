package ch.hslu.informatik.gastgewerbe.persister;

import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.model.Produkt;

import java.util.List;

public interface BestellungPositionDAO extends GenericPersisterDAO<BestellungPosition>{

    /*
     * Liefert alle BestellungPosition-Objekte für den übergebenen Boolean Bestellungsstatus
     * zurück, falls welche vorhanden, sonst eine leere Liste.
     *

     */
    List<BestellungPosition> findByBereit(Boolean bestellungBereit) throws Exception;


    /*
     * Liefert alle BestellungPosition-Objekte für den übergebenen Boolean Auslieferungsstatus
     * zurück, falls welche vorhanden, sonst eine leere Liste.
     *

     */

    List<BestellungPosition> findByAusgeliefert(Boolean bestellungAusgeliefert) throws Exception;
}
