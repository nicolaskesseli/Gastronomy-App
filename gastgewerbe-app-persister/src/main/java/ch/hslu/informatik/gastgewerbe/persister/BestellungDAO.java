package ch.hslu.informatik.gastgewerbe.persister;

import java.time.LocalDateTime;
import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;

public interface BestellungDAO extends GenericPersisterDAO<Bestellung> {
	
	
	
	
	
	List<Bestellung> findByTischNr(int TischNr) throws Exception;

	List<Bestellung> findByZeit(LocalDateTime zeit) throws Exception;

	/*
	 * Liefert alle Bestellung-Objekte für den übergebenen Boolean Bezahlungsstatus
	 * zurück, falls welche vorhanden, sonst eine leere Liste.
	 *

	 */

	List<Bestellung> findByRechBezahlt(Boolean rechnungBezahlt) throws Exception;

	/*
	 * Liefert alle Bestellung-Objekte für den übergebenen Boolean Bestellungsstatus
	 * zurück, falls welche vorhanden, sonst eine leere Liste.
	 *
	 */

	List<Bestellung> findByBereit(Boolean bestellungBereit) throws Exception;

	/*
	 * Liefert alle Bestellung-Objekte für den übergebenen Boolean Auslieferungsstatus
	 * zurück, falls welche vorhanden, sonst eine leere Liste.
	 *
	 */

	List<Bestellung> findByAusgeliefert(Boolean bestellungAusgeliefert) throws Exception;
	
	/*
	 * Liefert alle Bestellung-Objekte für den übergebenen Boolean RechnungsStatus und Tisch-Nr.
	 * zurück, falls welche vorhanden, sonst eine leere Liste.
	 *
	 */

	List<Bestellung> findByRechBezahltTisch(int TischNr, Boolean rechnungBezahlt) throws Exception;
	
	

}
