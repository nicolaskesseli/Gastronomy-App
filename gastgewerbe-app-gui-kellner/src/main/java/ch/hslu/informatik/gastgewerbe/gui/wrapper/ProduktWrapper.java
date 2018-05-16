package ch.hslu.informatik.gastgewerbe.gui.wrapper;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.informatik.gastgewerbe.model.Produkt;

public class ProduktWrapper {
	
	private Produkt produkt;
	
	List<Produkt> pListe = new ArrayList<>();
	
	public ProduktWrapper() {
				
	}
	
	public ProduktWrapper(List<Produkt> pListe) {
		
	}

	public ProduktWrapper(Produkt produkt) {
		this.produkt = produkt;
	}
	
	public Produkt getProdukt() {
		return produkt;
	}
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	public long getId() {
		return produkt.getId();
	}
	public void setId(long id) {
		produkt.setId(id);
	}
	public String getName() {
		return produkt.getName();
	}
	public double getPreis() {
		return produkt.getPreis();
	}
	public void setPreis(double preis) {
		produkt.setPreis(preis);
	}
	public void setName(String name) {
		produkt.setName(name);
	}
}
