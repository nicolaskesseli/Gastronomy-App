package ch.hslu.informatik.rmi.impl;

import ch.hslu.informatik.gastgewerbe.api.AbrechnungService;
import ch.hslu.informatik.gastgewerbe.businessabrechnung.AbrechnungManager;
import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.Bestellung;
import ch.hslu.informatik.gastgewerbe.model.Tisch;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiAbrechnungService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class RmiAbrechnungServiceImpl extends UnicastRemoteObject implements RmiAbrechnungService {

	private AbrechnungService abrechnungService;

	public RmiAbrechnungServiceImpl() throws RemoteException {

	}

	public AbrechnungService getAbrechnungService() {
		if (abrechnungService == null) {
			abrechnungService = new AbrechnungManager();
		}

		return abrechnungService;
	}

	@Override
	public double tischAbrechnen(Tisch tisch, Benutzer benutzer, Bestellung bestellung) throws Exception {
		return getAbrechnungService().tischAbrechnen(tisch, benutzer, bestellung);
	}

	@Override
	public double abschluss(LocalDateTime zeit) throws Exception {
		return getAbrechnungService().abschluss(zeit);
	}

	@Override
	public List<Abrechnung> findByBenutzerUndDatum(Benutzer benutzer, LocalDateTime zeit) throws Exception {
		return getAbrechnungService().findByBenutzerUndDatum(benutzer, zeit);
	}

	@Override
	public Tisch findByTischNr(int TischNr) throws Exception {
		return getAbrechnungService().findByTischNr(TischNr);
	}
}
