package ch.hslu.informatik.gastgewerbe.api;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BestellungService {

    //Bestellung am Tisch erfassen
    Bestellung bestellen(Bestellung bestellung) throws Exception;

    // Bestellungsposition zu bestender Bestellung hinzufügen
    Bestellung bestellungAktualisieren(Bestellung bestellung) throws Exception;

    // Bestellungsposition bereit
    boolean bestellungPositionBereit(BestellungPosition bestellungPosition) throws Exception;

    // Liefert alle offenen Bestellung
    List<Bestellung> alleBestellungen() throws Exception;

    // Liefert alle offenen Bestellungen einer Tisch Nr.
    List<Bestellung> findByTischNr(int TischNr) throws Exception;

    // Liefer alle offenen Bestellungen für das bestimmte Datum
    List<Bestellung> findByZeit(LocalDateTime zeit) throws Exception;

    // Liefert Bestellung mit dieser ID
    Bestellung findById(Long id) throws Exception;

    // Löscht übergebene Bestellung
    void deletBestellung(Bestellung bestellung) throws Exception;

    // Liefert BestellungsPos für diese ID
    BestellungPosition bestPosFindById(Long id) throws Exception;

}
