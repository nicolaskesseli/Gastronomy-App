package ch.hslu.informatik.gastgewerbe.businessprodukt;

import ch.hslu.informatik.gastgewerbe.api.ProduktService;
import ch.hslu.informatik.gastgewerbe.model.Produkt;

import java.util.List;

public class ProduktManager implements ProduktService {

    @Override
    public Produkt produktHinzufuegen(Produkt produkt) throws Exception {
        return null;
    }

    @Override
    public void produktLoeschen(Produkt produkt) throws Exception {

    }

    @Override
    public Produkt produktAktualisieren(Produkt produkt) throws Exception {
        return null;
    }

    @Override
    public Produkt findProdukt(String bezeichnung) throws Exception {
        return null;
    }

    @Override
    public Produkt findProduktByCode(int ProduktId) throws Exception {
        return null;
    }

    @Override
    public List<Produkt> alleProdukt() {
        return null;
    }

    @Override
    public List<Produkt> alleProduktNachKategorie(String kategorie) {
        return null;
    }
}
