package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "Bestellung.findByRechBezahlt", query = "SELECT e FROM Bestellung e WHERE e.rechnungBezahlt=:rechnungBezahlt"),
	@NamedQuery(name = "Bestellung.findByZeit", query = "SELECT e FROM Bestellung e WHERE e.zeit=:zeit"),
	@NamedQuery(name = "Bestellung.findByTischNr", query = "SELECT e FROM Bestellung e WHERE e.tisch.tischNr=:tischNr"),
	@NamedQuery(name = "Bestellung.findByBereit", query = "SELECT e FROM Bestellung e,  BestellungPosition p WHERE p.bestellungBereit =:bestellungBereit"),
	@NamedQuery(name = "Bestellung.findByAusgeliefert", query = "SELECT e FROM Bestellung e,  BestellungPosition p WHERE p.bestellungAusgeliefert =:bestellungAusgeliefert")
})

public class Bestellung implements Serializable {

	private static final long serialVersionUID = 7279601401001555811L;

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Tisch tisch;

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	List<BestellungPosition> bestellungPositionListe = new ArrayList<BestellungPosition>();

    private String bemerkung;
    private boolean rechnungBezahlt;
    private LocalDateTime zeit;

	public Bestellung() {
	
	}



	public Bestellung(String bemerkung, Tisch tisch) {
		this.bemerkung = bemerkung;
		this.tisch = tisch;
		this.rechnungBezahlt=false;
		this.zeit=LocalDateTime.now();
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



	public LocalDateTime getZeit() {
		return zeit;
	}



	public void setZeit(LocalDateTime zeit) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Bestellung)) return false;
		Bestellung that = (Bestellung) o;
		return id == that.id &&
				rechnungBezahlt == that.rechnungBezahlt &&
				Objects.equals(tisch, that.tisch) &&
				Objects.equals(bestellungPositionListe, that.bestellungPositionListe) &&
				Objects.equals(bemerkung, that.bemerkung) &&
				Objects.equals(zeit, that.zeit);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, tisch, bestellungPositionListe, bemerkung, rechnungBezahlt, zeit);
	}

	@Override
	public String toString() {
		return "Bestellung{" +
				"id=" + id +
				", tisch=" + tisch +
				", bestellungPositionListe=" + bestellungPositionListe +
				", bemerkung='" + bemerkung + '\'' +
				", rechnungBezahlt=" + rechnungBezahlt +
				", zeit=" + zeit +
				'}';
	}
}
