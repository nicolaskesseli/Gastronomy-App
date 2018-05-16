package ch.hslu.informatik.gastgewerbe.gui.wrapper;

import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;

public class BestellungPositionWrapper {

    private BestellungPosition bestellungPosition;

    public BestellungPositionWrapper(){

    }

    public BestellungPositionWrapper(BestellungPosition bestellungPosition){
        this.bestellungPosition = bestellungPosition;

    }

    public long getId() {

        return bestellungPosition.getId();
    }

    public void setId(long id) {

        bestellungPosition.setId(id);
    }

    public String getProdukt() {

        return bestellungPosition.getProdukt().getName();
    }

    public void setProdukt(String produkt) {

        bestellungPosition.getProdukt().setName(produkt);
    }

    public int getAnzahl() {

        return bestellungPosition.getAnzahl();
    }

    public void setAnzahl(int anzahl) {

        bestellungPosition.setAnzahl(anzahl);
    }


    public BestellungPosition getBestellungPosition() {
        return bestellungPosition;
    }

    public void setBestellungPosition(BestellungPosition bestellungPosition) {
        this.bestellungPosition = bestellungPosition;
    }
}
