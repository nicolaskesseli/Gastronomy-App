package ch.hslu.informatik.gastgewerbe.api;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;

import java.time.LocalDate;
import java.util.List;

public interface BestellungService {

    //Bestellung am Tisch erfassen
    Bestellung bestellen(Bestellung bestellung) throws Exception;

    // Bestellungsposition zu bestender Bestellung hinzufügen
    Bestellung bestellungPosHinzuefugen(Bestellung bestellung) throws Exception;

    // Bestellungsposition zu bestender Bestellung löschen
    Bestellung bestellungPosLoeschen(Bestellung bestellung) throws Exception;

    // Liefert alle offenen Bestellung
    List<Bestellung> alleBestellungen() throws Exception;

    // Liefert alle offenen Bestellungen einer Tisch Nr.
    List<Bestellung> findByTischNr(int TischNr) throws Exception;

    // Liefer alle offenen Bestellungen für das bestimmte Datum
    List<Bestellung> findByZeit(LocalDate zeit) throws Exception;



}
