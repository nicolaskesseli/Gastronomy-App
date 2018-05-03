package ch.hslu.informatik.gastgewerbe.rmi;

import ch.hslu.informatik.gastgewerbe.api.LoginService;
import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.rmi.api.RmiLoginService;
import ch.hslu.informatik.gastgewerbe.verteiler.business.login.LoginManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiLoginServiceImpl extends UnicastRemoteObject implements RmiLoginService {

    private LoginService loginService;

    public RmiLoginServiceImpl() throws RemoteException {

    }

    public LoginService getLoginService() {

        if (loginService == null) {
            loginService = new LoginManager();
        }

        return loginService;
    }

    public Benutzer login(String benutzername, String kennwort) throws Exception {
        return getLoginService().login(benutzername, kennwort);
    }

    public boolean kennwortAendern(String benutzername, String kennwortAktuell, String kennwortNeu) throws Exception {
        return getLoginService().kennwortAendern(benutzername, kennwortAktuell, kennwortNeu);
    }
}
