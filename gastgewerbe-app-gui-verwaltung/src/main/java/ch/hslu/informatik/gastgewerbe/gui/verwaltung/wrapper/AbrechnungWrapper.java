package ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper;

import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;

import java.time.LocalDateTime;

public class AbrechnungWrapper {

    private Abrechnung abrechnung;

    public AbrechnungWrapper(Abrechnung abrechnung) {
        this.abrechnung = abrechnung;
    }

    public Abrechnung getAbrechnung () {
        return abrechnung;
    }

    public void setAbrechnung (Abrechnung abrechnung) {
         this.abrechnung = abrechnung;
    }

    public LocalDateTime getZeitAbrechnung() {
        return abrechnung.getZeit();
    }

    public int getPlz() {
        return abrechnung.getBenutzer().getAdresse().getPlz();
    }

    public String getOrt() {
        return abrechnung.getBenutzer().getAdresse().getOrt();
    }

    public String getEmail() {
        return abrechnung.getBenutzer().getKontakt().getEmail();
    }

    public String getTelefon() {
        return abrechnung.getBenutzer().getKontakt().getTelefon();
    }

    public String getBenutzername() {
        return abrechnung.getBenutzer().getCredentials().getBenutzername();
    }

    public String getKennwort() {
        return abrechnung.getBenutzer().getCredentials().getPasswort();
    }

    public String getRolle() {
        return abrechnung.getBenutzer().getRolle().toString();
    }

    public long getId() {
        return abrechnung.getBestellung().getId();
    }

    public int getTischNr(){
        return abrechnung.getBestellung().getTisch().getTischNr();
    }

    public LocalDateTime getZeitBestellung(){
        return abrechnung.getBestellung().getZeit();
    }

    public String getBemerkung() {
        return abrechnung.getBestellung().getBemerkung();
    }

}
