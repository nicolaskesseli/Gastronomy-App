package ch.hslu.informatik.gastgewerbe.api;

import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.Produkt;

public interface ProduktService {

	//Fügt neuen Artikel hinzu
	Produkt produktHinzufuegen(Produkt produkt) throws Exception;
	
	
	//Löscht Artikel
	void produktLoeschen(Produkt produkt) throws Exception;
	
	
	//Ändert bestehenden Artikel
	Produkt produktAktualisieren(Produkt produkt) throws Exception;
	
	//Findet Artikel mit ent. produktCode
	Produkt findByProduktCode(String produktCode) throws Exception;

	//Findet Liste von Produkte anhand des Namen
	List<Produkt> findProduktByName(String name) throws Exception;
	
	
	//Zeigt alle Pordukte an
	List<Produkt> alleProdukt()throws Exception;
	
	
	//Zeigt alle Produkte aus gewünschter Kategorie (Getränk / Gericht / SNACK)
	List<Produkt> alleProduktNachKategorie(String kategorie) throws Exception;
	
	
	
}
