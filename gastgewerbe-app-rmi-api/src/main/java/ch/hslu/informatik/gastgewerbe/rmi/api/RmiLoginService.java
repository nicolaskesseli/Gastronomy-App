package ch.hslu.informatik.gastgewerbe.rmi.api;

import ch.hslu.informatik.gastgewerbe.api.LoginService;

import java.rmi.Remote;

public interface RmiLoginService extends LoginService, Remote {

	public static final String REMOTE_OBJECT_NAME = "LOGIN_SERVICE_RO";
}
