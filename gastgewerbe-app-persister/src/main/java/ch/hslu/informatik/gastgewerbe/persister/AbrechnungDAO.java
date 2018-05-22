package ch.hslu.informatik.gastgewerbe.persister;

import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;

import java.time.LocalDateTime;
import java.util.List;

public interface AbrechnungDAO extends GenericPersisterDAO<Abrechnung>{

    /**
            * Liefert alle Rechnungen für das übergebene Datum zurück, falls welche
     * vorhanden, sonst eine leere Liste.
            *
            * @param LocalDateTime
     * @return
             * @throws Exception
     */
    List<Abrechnung> findByDatum(LocalDateTime zeit) throws Exception;

    /**
     * Liefert alle Rechnungen für den übergebene Benutzer zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param benutzer
     * @return
     * @throws Exception
     */
    List<Abrechnung> findByBenutzer(Benutzer benutzer) throws Exception;

    /**
     * Liefert alle Rechnungen für die übergebenen Benutzer und Datum zurück,
     * falls welche vorhanden, sonst eine leere Liste.
     *
     * @param person
     * @param LocalDateTime
     * @return
     * @throws Exception
     */
    List<Abrechnung> findByBenutzerUndDatum(Benutzer benutzer, LocalDateTime zeit) throws Exception;
}
