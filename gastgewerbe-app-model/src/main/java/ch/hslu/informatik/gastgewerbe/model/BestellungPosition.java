package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "BestellungPosition.findByProduktTyp", query = "SELECT e FROM BestellungPosition e WHERE e.produktTyp=:produktTyp")
public class BestellungPosition implements Serializable {

	private static final long serialVersionUID = -1508291776956970574L;

	@Id
	@GeneratedValue
	private long id;
	private Produkt produkt;
	private boolean bestellungBereit;
	private int anzahl;
	
	public BestellungPosition(long id, Produkt produkt, boolean bestellungBereit, int anzahl) {
		super();
		this.id = id;
		this.produkt = produkt;
		this.bestellungBereit = bestellungBereit;
		this.anzahl = anzahl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public boolean isBestellungBereit() {
		return bestellungBereit;
	}

	public void setBestellungBereit(boolean bestellungBereit) {
		this.bestellungBereit = bestellungBereit;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BestellungPosition other = (BestellungPosition) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BestellungPosition [id=" + id + ", produkt=" + produkt + ", bestellungBereit=" + bestellungBereit
				+ ", anzahl=" + anzahl + "]";
	}
	

}
