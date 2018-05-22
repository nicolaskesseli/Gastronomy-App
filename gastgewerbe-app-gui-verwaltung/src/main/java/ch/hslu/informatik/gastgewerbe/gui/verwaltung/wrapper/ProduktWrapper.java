package ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper;


import ch.hslu.informatik.gastgewerbe.model.Produkt;

public class ProduktWrapper {

	private Produkt produkt;

	public ProduktWrapper(Produkt produkt) {
		this.produkt = produkt;
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

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

}
