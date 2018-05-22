package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@NamedQueries({
@NamedQuery(name = "BestellungPosition.findByBereit", query = "SELECT e FROM BestellungPosition e WHERE e.bestellungBereit=:bestellungBereit"),
@NamedQuery(name = "BestellungPosition.findByAusgeliefert", query = "SELECT e FROM BestellungPosition e WHERE e.bestellungAusgeliefert=:bestellungAusgeliefert")
})
public class BestellungPosition implements Serializable {

	private static final long serialVersionUID = -1508291776956970574L;

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Produkt produkt;

	private boolean bestellungBereit;
	private int anzahl;
	private boolean bestellungAusgeliefert;



	public BestellungPosition(Produkt produkt, int anzahl) {
		this.produkt = produkt;
		this.anzahl = anzahl;
		this.bestellungBereit=false;
		this.bestellungAusgeliefert=false;
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

	public boolean isBestellungAusgeliefert() {
		return bestellungAusgeliefert;
	}

	public void setBestellungAusgeliefert(boolean bestellungAusgeliefert) {
		this.bestellungAusgeliefert = bestellungAusgeliefert;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BestellungPosition)) return false;
		BestellungPosition that = (BestellungPosition) o;
		return id == that.id &&
				bestellungBereit == that.bestellungBereit &&
				anzahl == that.anzahl &&
				bestellungAusgeliefert == that.bestellungAusgeliefert &&
				Objects.equals(produkt, that.produkt);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, produkt, bestellungBereit, anzahl, bestellungAusgeliefert);
	}

	@Override
	public String toString() {
		return "BestellungPosition{" +
				"id=" + id +
				", produkt=" + produkt +
				", bestellungBereit=" + bestellungBereit +
				", anzahl=" + anzahl +
				", bestellungAusgeliefert=" + bestellungAusgeliefert +
				'}';
	}
}
