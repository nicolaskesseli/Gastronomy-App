package ch.hslu.informatik.gastgewerbe.api;

import ch.hslu.informatik.gastgewerbe.model.Benutzer;

public interface LoginService {

	/*Methode um sich einzuloggen*/
	Benutzer login(String benutzername, String kennwort) throws Exception;

	//Methode, um das Kennwort zu ändern
	boolean kennwortAendern(String benutzername, String kennwortAktuell, String kennwortNeu) throws Exception;
	

}
