package ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper;

import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.Tisch;

public class TischWrapper {

    private Tisch tisch;

    public TischWrapper(Tisch tisch) {
        this.tisch = tisch;
    }

    public Tisch getTisch() {
        return tisch;
    }

    public int getTischNummer() {
        return tisch.getTischNr();
    }
}
