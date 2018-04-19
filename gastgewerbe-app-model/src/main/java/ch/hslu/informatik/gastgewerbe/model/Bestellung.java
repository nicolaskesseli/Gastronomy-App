package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
@NamedQuery(name = "Bestellung.findByDatum", query = "SELECT e FROM Bestellung e WHERE e.datum=:datum") })

public class Bestellung implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7279601401001555811L;

	@Id
	@GeneratedValue
	private long id;
	private Tisch tisch;
	private LocalDate zeit;
	private String bemerkung;
	private boolean rechnungBezahlt;

	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	
	List<BestellungPosition> bestellungPositionListe = new ArrayList<BestellungPosition>();

	public Bestellung() {
	
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Tisch getTisch() {
		return tisch;
	}

	public void setTisch(Tisch tisch) {
		this.tisch = tisch;
	}



	public LocalDate getZeit() {
		return zeit;
	}



	public void setZeit(LocalDate zeit) {
		this.zeit = zeit;
	}



	public String getBemerkung() {
		return bemerkung;
	}



	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}


	public boolean isRechnungBezahlt() {
		return rechnungBezahlt;
	}

	public void setRechnungBezahlt(boolean rechnungBezahlt) {
		this.rechnungBezahlt = rechnungBezahlt;
	}

	public List<BestellungPosition> getBestellungPositionListe() {
		return bestellungPositionListe;
	}

	public void setBestellungPositionListe(List<BestellungPosition> bestellungPositionListe) {
		this.bestellungPositionListe = bestellungPositionListe;
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
		Bestellung other = (Bestellung) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bestellung [id=" + id + ", tisch=" + tisch + ", zeit=" + zeit + ", bemerkung=" + bemerkung
				+ ", rechnungBezahlt=" + rechnungBezahlt + ", bestellungPositionListe=" + bestellungPositionListe + "]";
	}
	
}
