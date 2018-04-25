package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
@NamedQuery(name = "Bestellung.findByZeit", query = "SELECT e FROM Bestellung e WHERE e.zeit=:zeit"),
@NamedQuery(name="Bestellung.findByTischNr", query = "SELECT e FROM Bestellung e WHERE e.tisch.tischNr=:tischNr")
})

public class Bestellung implements Serializable {

	private static final long serialVersionUID = 7279601401001555811L;

	@Id
	@GeneratedValue
	private long id;

	private String bemerkung;
	private boolean rechnungBezahlt;
	private LocalDate zeit;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Tisch tisch;

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	List<BestellungPosition> bestellungPositionListe = new ArrayList<BestellungPosition>();

	public Bestellung() {
	
	}



	public Bestellung(String bemerkung, Tisch tisch) {
		this.bemerkung = bemerkung;
		this.tisch = tisch;
		this.rechnungBezahlt=false;
		this.zeit=LocalDate.now();
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
