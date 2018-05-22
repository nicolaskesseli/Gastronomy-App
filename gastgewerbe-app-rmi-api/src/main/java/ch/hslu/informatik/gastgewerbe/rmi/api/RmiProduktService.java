package ch.hslu.informatik.gastgewerbe.rmi.api;

import ch.hslu.informatik.gastgewerbe.api.ProduktService;

import java.rmi.Remote;

public interface RmiProduktService extends ProduktService,Remote {

    public static final String REMOTE_OBJECT_NAME = "PRODUKT_SERVICE_RO";
}
