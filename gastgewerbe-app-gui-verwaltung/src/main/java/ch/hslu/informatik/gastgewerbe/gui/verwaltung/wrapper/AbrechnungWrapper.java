package ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper;


import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import javafx.scene.control.CheckBox;

import java.time.LocalDateTime;

public class AbrechnungWrapper {

    private CheckBox ausgewaelt;

    private Abrechnung abrechnung;

    public AbrechnungWrapper(){

    }

    public AbrechnungWrapper(Abrechnung abrechnung) {

        this.abrechnung= abrechnung;
        this.ausgewaelt=new CheckBox();
    }

    public Abrechnung getAbrechnung() {
        return abrechnung;
    }

    public void setAbrechnung(Abrechnung abrechnung) {
        this.abrechnung = abrechnung;
    }

    public CheckBox getAusgewaelt() {
        return ausgewaelt;
    }

    public void setAusgewaelt(CheckBox ausgewaelt) {
        this.ausgewaelt = ausgewaelt;
    }

    public void setBenutzer (Benutzer benutzer){
        this.abrechnung.setBenutzer(benutzer);
    }

    public Benutzer getBenutzer(){
        return abrechnung.getBenutzer();
    }

    public void setZeit(LocalDateTime zeit){
        this.abrechnung.setZeit(zeit);
    }

    public LocalDateTime getZeit(){

        return abrechnung.getZeit();
    }

    public void setBetrag(Double betrag) {
        this.abrechnung.setBetrag(betrag);
    }

    public Double getBetrag() {

        return abrechnung.getBetrag();
    }

    public boolean isTagesAbrechnung() {
        return abrechnung.isTagesAbrechnung();
    }

    public void setTagesAbrechnung(boolean tagesAbrechnung) {

        this.abrechnung.setTagesAbrechnung(tagesAbrechnung);
    }
}
