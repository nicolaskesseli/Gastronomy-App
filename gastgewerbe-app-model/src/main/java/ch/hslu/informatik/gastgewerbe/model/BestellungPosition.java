package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@NamedQuery(name = "BestellungPosition.findByProdukt", query = "SELECT e FROM BestellungPosition e WHERE e.produkt=:produkt")
public class BestellungPosition implements Serializable {

	private static final long serialVersionUID = -1508291776956970574L;

	@Id
	@GeneratedValue
	private long id;

	private boolean bestellungBereit;
	private int anzahl;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Produkt produkt;


	
	public BestellungPosition(Produkt produkt, int anzahl) {
		this.produkt = produkt;
		this.anzahl = anzahl;
		this.bestellungBereit=false;
	}


	public BestellungPosition() {

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
