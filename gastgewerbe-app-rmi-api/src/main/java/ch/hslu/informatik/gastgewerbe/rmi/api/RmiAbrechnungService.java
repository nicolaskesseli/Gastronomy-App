package ch.hslu.informatik.gastgewerbe.rmi.api;

import ch.hslu.informatik.gastgewerbe.api.AbrechnungService;

import java.rmi.Remote;

public interface RmiAbrechnungService extends AbrechnungService, Remote {

    public static final String REMOTE_OBJECT_NAME = "ABRECHNUNG_SERVICE_RO";


}
