package ch.hslu.informatik.gastgewerbe.rmi.api;

import ch.hslu.informatik.gastgewerbe.api.BenutzerService;

import java.rmi.Remote;

public interface RmiBenutzerService extends BenutzerService, Remote {

	public static final String REMOTE_OBJECT_NAME = "BENUTZER_SERVICE_RO";

}
