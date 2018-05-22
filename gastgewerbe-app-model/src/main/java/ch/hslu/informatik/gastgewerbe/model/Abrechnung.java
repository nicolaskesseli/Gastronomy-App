package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "Abrechnung.findByDatum", query = "SELECT e FROM Abrechnung e WHERE e.zeit BETWEEN :startDatum AND :endDatum"),
		@NamedQuery(name = "Abrechnung.findByBenutzer", query = "SELECT e FROM Abrechnung e WHERE e.benutzer=:benutzer"),
		@NamedQuery(name = "Abrechnung.findByBenutzerUndDatum", query = "SELECT e FROM Abrechnung e WHERE e.zeit BETWEEN :startDatum AND :endDatum AND e.benutzer=:benutzer") })

public class Abrechnung implements Serializable {

	private static final long serialVersionUID = 4575490394462466750L;
	/* Ein produkt entfernen geht nur über Bestellung/BestellungPos!!!!! weil ManyToOne */
	@Id
	@GeneratedValue
	private long id;

	// Zu abrechende Bestellung(en)
	@ManyToOne(fetch = FetchType.EAGER)
	private Bestellung bestellung;

	 // Der an der Kasse angemeldetet Benutzer, der die Rechnung erstellt hat
	@ManyToOne(fetch = FetchType.EAGER)
	private Benutzer benutzer;

	private LocalDateTime zeit;
	private Double betrag;
	private boolean tagesAbrechnung;

	public Abrechnung() {

	}

	public Abrechnung(Benutzer benutzer, Bestellung bestellung, LocalDateTime zeit) {
		this.zeit = zeit;
		this.benutzer = benutzer;
		this.bestellung = bestellung;
		this.tagesAbrechnung = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getZeit() {
		return zeit;
	}

	public void setZeit(LocalDateTime zeit) {
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

	public Double getBetrag() {
		return betrag;
	}
	

	public boolean isTagesAbrechnung() {
		return tagesAbrechnung;
	}

	public void setTagesAbrechnung(boolean tagesAbrechnung) {
		this.tagesAbrechnung = tagesAbrechnung;
	}

	// Liefert dem Gesamtbetrag für die Abrechnung
	public Double getGesamtBetrag(){

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
		return id == that.id &&
				tagesAbrechnung == that.tagesAbrechnung &&
				Objects.equals(bestellung, that.bestellung) &&
				Objects.equals(benutzer, that.benutzer) &&
				Objects.equals(zeit, that.zeit) &&
				Objects.equals(betrag, that.betrag);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, bestellung, benutzer, zeit, betrag, tagesAbrechnung);
	}

	@Override
	public String toString() {
		return "Abrechnung{" +
				"id=" + id +
				", bestellung=" + bestellung +
				", benutzer=" + benutzer +
				", zeit=" + zeit +
				", betrag=" + betrag +
				", tagesAbrechnung=" + tagesAbrechnung +
				'}';
	}
}


	

