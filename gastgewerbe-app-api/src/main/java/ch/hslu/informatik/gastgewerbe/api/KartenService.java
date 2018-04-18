package ch.hslu.informatik.gastgewerbe.api;

import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.Produkt;

public interface KartenService {

	//Fügt neuen Artikel zur Karte hinzu
	Produkt produktHinzufuegen(Produkt produkt) throws Exception;
	
	
	//Löscht Artikel aus Karte
	void produktLoeschen(Produkt produkt) throws Exception;
	
	
	//Ändert bestehenden Artikel
	Produkt produktAktualisieren(Produkt produkt) throws Exception;
	
	
	//Findet Artikel mit ent. Bezeichnung
	Produkt findProdukt(String bezeichnung) throws Exception;
	
	
	//Findet Artikel mit ent. ID
	Produkt findProdukt(int ProduktId) throws Exception;
	
	
	//Zeigt alle Artikel an
	List<Produkt> alleProdukt();
	
	
	//Zeigt alle Artikel aus gewünschter Kategorie (Getränk / Gericht)
	List<Produkt> alleProduktNachTyp(String kategorie);
	
	
	
}
