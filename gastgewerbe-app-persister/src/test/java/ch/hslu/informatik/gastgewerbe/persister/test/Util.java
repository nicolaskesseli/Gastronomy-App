package ch.hslu.informatik.gastgewerbe.persister.test;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.Adresse;
import ch.hslu.informatik.gastgewerbe.model.Kontakt;
import ch.hslu.informatik.gastgewerbe.model.Person;


public class Util {
	public static List<Adresse> createAdresseListe() {

		List<Adresse> liste = new ArrayList<>();

		liste.add(new Adresse("Pilatusstrasse 8", 6000, "Luzern"));
		liste.add(new Adresse("Lindenstrasse 24", 6048, "Horw"));
		liste.add(new Adresse("Amlehnstrasse 18", 6010, "Kriens"));

		return liste;
	}

	public static List<Person> createPersonListe() {

		List<Person> liste = new ArrayList<>();

		liste.add(new Person("Studer", "Peter", new Adresse("Kichrgasse 2", 6010, "Kriens"),
				new Kontakt("pmeier@gmx.ch", "079 999 99 99")));
		liste.add(new Person("Weber", "Beat", new Adresse("Lindenstrasse 12", 6000, "Luzern"),
				new Kontakt("bweber@gmx.ch", "079 888 88 88")));
		liste.add(new Person("Bucher", "Marco", new Adresse("Bundesstrasse 122", 6000, "Luzern"),
				new Kontakt("mbucher@gmx.ch", "079 777 77 77")));

		return liste;
	}
}
