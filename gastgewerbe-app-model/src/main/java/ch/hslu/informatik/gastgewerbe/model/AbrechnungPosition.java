package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class AbrechnungPosition implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5302569904423398173L;

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Produkt produkt;

	private int anzahl;

	public AbrechnungPosition() {

	}

	public AbrechnungPosition(Produkt produkt, int anzahl) {
		this.produkt = produkt;
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
		result = prime * result + anzahl;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((produkt == null) ? 0 : produkt.hashCode());
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
		AbrechnungPosition other = (AbrechnungPosition) obj;
		if (anzahl != other.anzahl)
			return false;
		if (id != other.id)
			return false;
		if (produkt == null) {
			if (other.produkt != null)
				return false;
		} else if (!produkt.equals(other.produkt))
			return false;
		return true;
	}

	/* Calculated Properties */
	public int getProduktId() {
		return produkt.getProduktId();
	}

	public String getBeschreibung() {
		return produkt.getBeschreibung();
	}

	public String getProduktIdUndBeschreibung() {
		int typ = produkt.getProduktId();
		String beschreibung = produkt.getBeschreibung();

		return typ + "\n" + beschreibung;
	}

	public double getPreis() {
		return produkt.getPreis();
	}

	public double getBetrag() {
		return anzahl * produkt.getPreis();
	}

}
