package ch.hslu.informatik.gastgewerbe.api;

import java.util.List;

public interface KartenService {

	//Fügt neuen Artikel zur Karte hinzu
	Konsumartikel konsumartikelHinzufuegen(Konsumartikel konsumartikel) throws Exception;
	
	
	//Löscht Artikel aus Karte
	void konsumartikelLoeschen(Konsumartikel Konsumartikel) throws Exception;
	
	
	//Ändert bestehenden Artikel
	Konsumartikel konsumartikelAktualisieren(Konsumartikel Konsumartikel) throws Exception;
	
	
	//Findet Artikel mit ent. Bezeichnung
	Konsumartikel findKonsumartikel(String bezeichnung) throws Exception;
	
	
	//Findet Artikel mit ent. ID
	Konsumartikel findKonsumartikel(int artikelId) throws Exception;
	
	
	//Zeigt alle Artikel an
	List<Konsumartikel> alleKonsumartikel();
	
	
	//Zeigt alle Artikel aus gewünschter Kategorie (Getränk / Gericht)
	List<Konsumartikel> alleKonsumartikelNachTyp(Kategorie kategorie);
	
	
	
}
