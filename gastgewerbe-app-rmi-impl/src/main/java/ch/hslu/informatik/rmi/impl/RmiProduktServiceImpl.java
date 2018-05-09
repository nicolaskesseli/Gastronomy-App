package ch.hslu.informatik.rmi.impl;

import ch.hslu.informatik.gastgewerbe.api.ProduktService;
import ch.hslu.informatik.gastgewerbe.businessprodukt.ProduktManager;
import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
import ch.hslu.informatik.gastgewerbe.model.Produkt;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiProduktService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RmiProduktServiceImpl extends UnicastRemoteObject implements RmiProduktService {

    private ProduktService produktService;

    public RmiProduktServiceImpl() throws RemoteException {

        }

        public ProduktService getProduktService(){
            if(produktService==null){
                produktService= new ProduktManager();

            }
            return produktService;
        }

    @Override
    public Produkt produktHinzufuegen(Produkt produkt) throws Exception {
        return getProduktService().produktHinzufuegen(produkt);
    }

    @Override
    public void produktLoeschen(Produkt produkt) throws Exception {
        getProduktService().produktLoeschen(produkt);

    }

    @Override
    public Produkt produktAktualisieren(Produkt produkt) throws Exception {
        return getProduktService().produktAktualisieren(produkt);
    }

    @Override
    public List<Produkt> findByProduktCode(String produktCode) throws Exception {
        return getProduktService().findByProduktCode(produktCode);
    }

    @Override
    public List<Produkt> findProduktByName(String name) throws Exception {
        return getProduktService().findProduktByName(name);
    }

    @Override
    public List<Produkt> findProduktByKategorie(KategorieTyp kategorie) throws Exception {
        return getProduktService().findProduktByKategorie(kategorie);
    }

    @Override
    public List<Produkt> alleProdukt() throws Exception {
        return getProduktService().alleProdukt();
    }
}
