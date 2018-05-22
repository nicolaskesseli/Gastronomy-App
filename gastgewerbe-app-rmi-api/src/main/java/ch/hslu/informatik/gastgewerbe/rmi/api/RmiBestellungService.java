package ch.hslu.informatik.gastgewerbe.rmi.api;

import ch.hslu.informatik.gastgewerbe.api.BestellungService;

import java.rmi.Remote;

public interface RmiBestellungService extends BestellungService, Remote {

	public static final String REMOTE_OBJECT_NAME = "BESTELLUNG_SERVICE_RO";

}
