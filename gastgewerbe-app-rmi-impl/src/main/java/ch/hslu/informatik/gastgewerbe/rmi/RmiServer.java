	package ch.hslu.informatik.gastgewerbe.rmi;

import ch.hslu.informatik.gastgewerbe.rmi.api.*;
import ch.hslu.informatik.rmi.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class RmiServer {

    private static Logger logger = LogManager.getLogger(RmiServer.class);

    private static final String PROPERTY_FILE_NAME = "rmi_server.properties";
    private static final String GASTGEWERBE_RMI_REGISTRY_PORT = "rmi.registry.port";

    public static void main(String[] args) {

        int portNr = 0;

        try {

            Properties props = new Properties();

            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE_NAME));

            String strPort = props.getProperty(GASTGEWERBE_RMI_REGISTRY_PORT);

            try {
                portNr = Integer.parseInt(strPort);
            } catch (NumberFormatException e) {
                String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
                logger.error(msg, e);
                System.out.println("\nFEHLER: " + msg + "\n");

                return;
            }

            Registry reg = LocateRegistry.createRegistry(portNr);

            if (reg != null) {

                /* Login-RemoteObject erstellen und binden */
                RmiLoginService loginServiceRO = new RmiLoginServiceImpl();
                reg.rebind(RmiLoginService.REMOTE_OBJECT_NAME, loginServiceRO);
                logger.info("Remote Object \'" + RmiLoginService.REMOTE_OBJECT_NAME + "\' bound!");

                // Abrechnung-RemoteObject erstellen und binden
                RmiAbrechnungService abrechnungServicRO = new RmiAbrechnungServiceImpl();
                reg.rebind(RmiAbrechnungService.REMOTE_OBJECT_NAME, abrechnungServicRO);
                logger.info("Remote Object \'" + RmiAbrechnungService.REMOTE_OBJECT_NAME + "\' bound!");

                // Benutzer-RemoteObject erstellen und binden
                RmiBenutzerService benutzerServiceRo = new RmiBenutzerServiceImpl();
                reg.rebind(RmiBenutzerService.REMOTE_OBJECT_NAME, benutzerServiceRo);
                logger.info("Remote Object \'" + RmiBenutzerService.REMOTE_OBJECT_NAME + "\' bound!");

                //Bestellung-RemoteObject erstellen und binden
                RmiBestellungService bestellungServiceRo = new RmiBestellungServiceImpl();
                reg.rebind(RmiBestellungService.REMOTE_OBJECT_NAME, bestellungServiceRo);
                logger.info("Remote Object \'" + RmiBestellungService.REMOTE_OBJECT_NAME + "\' bound!");

                //Produkt-remoteObject erstellen und binden
                RmiProduktService produktServiceRo= new RmiProduktServiceImpl();
                reg.rebind(RmiProduktService.REMOTE_OBJECT_NAME, produktServiceRo);
                logger.info("Remote Object \'" + RmiProduktService.REMOTE_OBJECT_NAME + "\' bound!");

                String ip = InetAddress.getLocalHost().getHostAddress();
                String titel = "RMI Server auf " + ip + ":" + portNr + " ist bereit";

                JOptionPane.showMessageDialog(null,
                        "<html>" + "<p style=\"text-align: center;\">GASTGEWERBE</p>" + "<br>"
                                + "Klicken Sie auf OK um RMI-Server herunterzufahren!" + "<br>" + "</html>",
                        titel, JOptionPane.INFORMATION_MESSAGE);

                /* RemoteObjects freigeben (unbind) */
                reg.unbind(RmiLoginService.REMOTE_OBJECT_NAME);
                logger.info("Remote Object \'" + RmiLoginService.REMOTE_OBJECT_NAME + "\' unbound!");

                reg.unbind(RmiAbrechnungService.REMOTE_OBJECT_NAME);
                logger.info("Remote Object \'" + RmiAbrechnungService.REMOTE_OBJECT_NAME + "\' unbound!");

                reg.unbind(RmiBestellungService.REMOTE_OBJECT_NAME);
                logger.info("Remote Object \'" + RmiBestellungService.REMOTE_OBJECT_NAME + "\' unbound!");

                reg.unbind(RmiProduktService.REMOTE_OBJECT_NAME);
                logger.info("Remote Object \'" + RmiProduktService.REMOTE_OBJECT_NAME + "\' unbound!");

                reg.unbind(RmiBenutzerService.REMOTE_OBJECT_NAME);
                logger.info("Remote Object \'" + RmiBenutzerService.REMOTE_OBJECT_NAME + "\' unbound!");

                /* Prozess beenden */
                System.exit(0);

            } else {

                String msg = "Registry konnte auf der Port \'" + portNr + "\' nicht gestartet  werden!";
                logger.error(msg);
                System.out.println("\nFEHLER: " + msg + "\n");

                return;
            }

        } catch (Exception e) {
            String msg = "RMI Server hat Fehler gemeldet";
            logger.error(msg, e);
            System.exit(-1);
        }

    }
}
