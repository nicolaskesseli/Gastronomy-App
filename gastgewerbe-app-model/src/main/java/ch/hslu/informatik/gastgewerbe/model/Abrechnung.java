package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "Abrechnung.findByDatum", query = "SELECT e FROM Abrechnung e WHERE e.zeit BETWEEN :startDatum AND :endDatum"),
		@NamedQuery(name = "Abrechnung.findByBenutzer", query = "SELECT e FROM Abrechnung e WHERE e.benutzer=:benutzer"),
		@NamedQuery(name = "Abrechnung.findByBenutzerUndDatum", query = "SELECT e FROM Abrechnung e WHERE e.zeit BETWEEN :startDatum AND :endDatum AND e.benutzer=:benutzer") })
public class Abrechnung implements Serializable {

	private static final long serialVersionUID = 4575490394462466750L;

	@Id
	@GeneratedValue
	private long id;

	private LocalDate zeit;
	private Double betrag;

	// Zu abrechende Bestellung
	@OneToOne(fetch = FetchType.EAGER)
	private Bestellung bestellung;


	/**
	 * Der an der Kasse angemeldetet Benutzer, der die Rechnung erstellt hat
	 * (Ersteller)
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	private Benutzer benutzer;

	public Abrechnung() {

	}

	public Abrechnung(Benutzer benutzer, Bestellung bestellung) {
		this.zeit = LocalDate.now();
		this.benutzer = benutzer;
		this.bestellung = bestellung;
		this.betrag=0.0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getZeit() {
		return zeit;
	}

	public void setZeit(LocalDate zeit) {
		this.zeit = zeit;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public Bestellung getBestellung() {
		return bestellung;
	}

	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	public void setBetrag(Double betrag) {
		this.betrag = betrag;
	}
    // Liefert dem Gesamtbetrag für die Abrechnung
	public Double getBetrag(){

	    double betrag = 0;

        List<BestellungPosition> liste = bestellung.getBestellungPositionListe();

        for (BestellungPosition a : liste){
            betrag = a.getProdukt().getPreis()*a.getAnzahl();
        }

		return betrag;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Abrechnung)) return false;

		Abrechnung that = (Abrechnung) o;

		if (id != that.id) return false;
		if (zeit != null ? !zeit.equals(that.zeit) : that.zeit != null) return false;
		if (betrag != null ? !betrag.equals(that.betrag) : that.betrag != null) return false;
		if (bestellung != null ? !bestellung.equals(that.bestellung) : that.bestellung != null) return false;
		return benutzer != null ? benutzer.equals(that.benutzer) : that.benutzer == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (zeit != null ? zeit.hashCode() : 0);
		result = 31 * result + (betrag != null ? betrag.hashCode() : 0);
		result = 31 * result + (bestellung != null ? bestellung.hashCode() : 0);
		result = 31 * result + (benutzer != null ? benutzer.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Abrechnung{" +
				"id=" + id +
				", zeit=" + zeit +
				", betrag=" + betrag +
				", bestellung=" + bestellung +
				", benutzer=" + benutzer +
				'}';
	}
}


	

