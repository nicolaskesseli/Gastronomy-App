package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@NamedQuery(name = "BestellungPosition.findByProdukt", query = "SELECT e FROM BestellungPosition e WHERE e.produkt=:produkt")
public class BestellungPosition implements Serializable {

	private static final long serialVersionUID = -1508291776956970574L;

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Produkt produkt;

	private boolean bestellungBereit;
	private int anzahl;



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

	public boolean isBestellungBereit() {
		return bestellungBereit;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BestellungPosition)) return false;
		BestellungPosition that = (BestellungPosition) o;
		return id == that.id &&
				bestellungBereit == that.bestellungBereit &&
				anzahl == that.anzahl &&
				Objects.equals(produkt, that.produkt);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, produkt, bestellungBereit, anzahl);
	}

	@Override
	public String toString() {
		return "BestellungPosition{" +
				"id=" + id +
				", produkt=" + produkt +
				", bestellungBereit=" + bestellungBereit +
				", anzahl=" + anzahl +
				'}';
	}
}
