package ch.hslu.informatik.gastgewerbe.rmi.api;

import ch.hslu.informatik.gastgewerbe.api.TischService;

import java.rmi.Remote;

public interface RmiTischService extends TischService, Remote {

	public static final String REMOTE_OBJECT_NAME = "TISCH_SERVICE_RO";

}
