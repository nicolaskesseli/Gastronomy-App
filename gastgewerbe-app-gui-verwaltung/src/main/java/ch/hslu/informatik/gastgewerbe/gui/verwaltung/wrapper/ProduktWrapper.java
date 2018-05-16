package ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper;

import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import ch.hslu.informatik.gastgewerbe.model.KategorieTyp;
import ch.hslu.informatik.gastgewerbe.model.Produkt;

public class ProduktWrapper {

	private int nummer;
	private Produkt produkt;

	public ProduktWrapper(int nummer, Produkt produkt) {
		this.nummer = nummer;
		this.produkt = produkt;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public String getProduktCode() {
		return produkt.getProduktCode();
	}

	public String getName() {
		return produkt.getName();
	}

	public String getBeschreibung() {
		return produkt.getBeschreibung();
	}

	public double getPreis() {
		return produkt.getPreis();
	}

	public Produkt getProdukt() {
		return produkt;
	}

}
