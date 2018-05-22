package ch.hslu.informatik.rmi.impl;

import ch.hslu.informatik.gastgewerbe.api.TischService;
import ch.hslu.informatik.gastgewerbe.businesstisch.TischManager;
import ch.hslu.informatik.gastgewerbe.model.Tisch;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiTischService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RmiTischServiceImpl extends UnicastRemoteObject implements RmiTischService {

	private static final long serialVersionUID = 5660541388281430955L;
	
	private TischService tischService;

    public RmiTischServiceImpl() throws RemoteException {

        }

        public TischService getTischService(){
            if(tischService==null){
                tischService= new TischManager();

            }
            return tischService;
        }

    @Override
    public Tisch tischHinzufuegen(Tisch tisch) throws Exception {
        return getTischService().tischHinzufuegen(tisch);
    }

    @Override
    public Tisch tischAktualisieren(Tisch tisch) throws Exception {
        return getTischService().tischAktualisieren(tisch);
    }

    @Override
    public void tischLoeschen(Tisch tisch) throws Exception {
        getTischService().tischLoeschen(tisch);

    }

    @Override
    public Tisch findByTischNummer(int tischNr) throws Exception {
        return getTischService().findByTischNummer(tischNr);
    }

    @Override
    public List<Tisch> alleTische() throws Exception {
        return getTischService().alleTische();
    }

}
