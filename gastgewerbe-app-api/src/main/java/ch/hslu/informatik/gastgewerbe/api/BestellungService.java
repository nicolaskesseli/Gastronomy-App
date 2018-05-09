package ch.hslu.informatik.gastgewerbe.api;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;

import java.time.LocalDate;
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
    List<Bestellung> findByZeit(LocalDate zeit) throws Exception;



}
