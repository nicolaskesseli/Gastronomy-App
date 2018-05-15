package ch.hslu.informatik.rmi.impl;

import ch.hslu.informatik.gastgewerbe.api.BestellungService;
import ch.hslu.informatik.gastgewerbe.businessbestellung.BestellungManager;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.BestellungPosition;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiBestellungService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class RmiBestellungServiceImpl extends UnicastRemoteObject implements RmiBestellungService {

    private BestellungService bestellungService;

    public RmiBestellungServiceImpl() throws RemoteException {

    }

    public BestellungService getBestellungService(){
        if(bestellungService==null){
            bestellungService= new BestellungManager();


        }
        return bestellungService;
    }

    @Override
    public Bestellung bestellen(Bestellung bestellung) throws Exception {
        return getBestellungService().bestellen(bestellung);
    }

    @Override
    public Bestellung bestellungAktualisieren(Bestellung bestellung) throws Exception {
        return getBestellungService().bestellungAktualisieren(bestellung);
    }

    @Override
    public boolean bestellungPositionBereit(BestellungPosition bestellungPosition) throws Exception {
        return getBestellungService().bestellungPositionBereit(bestellungPosition);
    }

    @Override
    public List<Bestellung> alleBestellungen() throws Exception {
        return getBestellungService().alleBestellungen();
    }

    @Override
    public List<Bestellung> findByTischNr(int TischNr) throws Exception {
        return getBestellungService().findByTischNr(TischNr);
    }

    @Override
    public List<Bestellung> findByZeit(LocalDate zeit) throws Exception {
        return getBestellungService().findByZeit(zeit);
    }

    @Override
    public void deletBestellung(Bestellung bestellung) throws Exception {
        getBestellungService().deletBestellung(bestellung);

    }

    @Override
    public Bestellung findById(Long id) throws Exception {
        return getBestellungService().findById(id);
    }

    @Override
    public BestellungPosition bestPosFindById(Long id) throws Exception {
        return getBestellungService().bestPosFindById(id);
    }
}
