package ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.Tisch;

public class TischWrapper {

    private Tisch tisch;
    private int nummer;

    public TischWrapper(Tisch tisch, int nummer) {
        this.tisch = tisch;
        this.nummer = nummer;
    }

    public Tisch getTisch() {
        return tisch;
    }

    public int getTischNr() {
        return tisch.getTischNr();
    }

    public void setTischNr(int tischNr){
        tisch.setTischNr(tischNr);
    }

    public int getNummer() {
        return nummer;
    }
}
