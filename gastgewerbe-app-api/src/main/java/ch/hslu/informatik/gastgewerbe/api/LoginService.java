package ch.hslu.informatik.gastgewerbe.api;

import ch.hslu.informatik.gastgewerbe.model.Benutzer;

public interface LoginService {
	
	Benutzer login(String benutzername, String kennwort) throws Exception;
		
	
	boolean kennwortAendern(String benutzername, String kennwortAktuell, String kennwortNeu) throws Exception;
	

}
