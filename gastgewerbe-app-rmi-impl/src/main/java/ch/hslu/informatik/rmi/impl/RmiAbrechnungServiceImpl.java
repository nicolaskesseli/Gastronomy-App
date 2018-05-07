package ch.hslu.informatik.rmi.impl;

import ch.hslu.informatik.gastgewerbe.api.AbrechnungService;
import ch.hslu.informatik.gastgewerbe.businessabrechnung.AbrechnungManager;
import ch.hslu.informatik.gastgewerbe.model.Tisch;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiAbrechnungService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiAbrechnungServiceImpl extends UnicastRemoteObject implements RmiAbrechnungService {

    private AbrechnungService abrechungService;

    public RmiAbrechnungServiceImpl() throws RemoteException {

    }

    public AbrechnungService getAbrechungService() {

        if (abrechungService == null) {
            abrechungService = new AbrechnungManager() {
            };
        }

        return abrechungService;
    }


    @Override
    public double tischAbrechnen(Tisch tisch) throws Exception {
        return getAbrechungService().tischAbrechnen(tisch);
    }

	@Override
	public double abschluss() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
