package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Abrechnung implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4575490394462466750L;

	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
	private Tisch tisch;
	private Bestellung bestellung;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate zeit;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<AbrechnungPosition> abrechnungPositionListe = new ArrayList<AbrechnungPosition>();

	/**
	 * Der an der Kasse angemeldetet Benutzer, der die Rechnung erstellt hat
	 * (Ersteller)
	 */
	@ManyToOne
	private Benutzer benutzer;

	public Abrechnung() {

	}

	public Abrechnung(LocalDate zeit, Benutzer benutzer, Tisch tisch, Bestellung bestellung) {
		this.zeit = zeit;
		this.benutzer = benutzer;
		this.tisch = tisch;
		this.bestellung = bestellung;
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

	public List<AbrechnungPosition> getRechnungPositionListe() {
		return abrechnungPositionListe;
	}

	public void setRechnungPositionListe(List<AbrechnungPosition> rechnungPositionListe) {
		this.abrechnungPositionListe = rechnungPositionListe;
	}

	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public Tisch getTisch() {
		return tisch;
	}

	public void setTisch(Tisch tisch) {
		this.tisch = tisch;
	}

	public Bestellung getBestellung() {
		return bestellung;
	}

	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
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
		Abrechnung other = (Abrechnung) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/* Helper Methoden */

	/**
	 * Fügt die übergebene RechnungPosition in die Liste ein.
	 *
	 * @param rechnungPosition
	 * @return
	 */
	public boolean addRechnungPosition(AbrechnungPosition rechnungPosition) {
		return abrechnungPositionListe.add(rechnungPosition);
	}

	/**
	 * Entferent die übergebene Rechnungsposition aus der Liste.
	 *
	 * @param rechnungPosition
	 * @return
	 */
	public boolean removeRechnungPosition(AbrechnungPosition rechnungPosition) {
		return abrechnungPositionListe.remove(rechnungPosition);
	}

	/**
	 * Liefert den Rechnungsbetrag zurück.
	 *
	 * @return
	 */
	public double getBetrag() {

		double betrag = 0;

		for (AbrechnungPosition rPosition : abrechnungPositionListe) {
			betrag += rPosition.getBetrag();
		}

		return betrag;
	}

	/**
	 * Liefert die Rechnungsposition für den übergebenen Produtktyp zurück.
	 *
	 * @param produktTyp
	 * @return
	 */
	public AbrechnungPosition findByProdukt(Produkt produkt) {

		for (AbrechnungPosition pos : abrechnungPositionListe) {
			if (pos.getProdukt().equals(produkt)) {
				return pos;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return "Abrechnung [id=" + id + ", tisch=" + tisch + ", bestellung=" + bestellung + ", zeit=" + zeit
				+ ", abrechnungPositionListe=" + abrechnungPositionListe + ", benutzer=" + benutzer + "]";
	}
	
	

//	/**
//	 * Liefert die Rechnungsposition für den übergebenen Produtktyp Code zurück.
//	 *
//	 * @param produktId
//	 * @return
//	 */
//	public AbrechnungPosition findByProduktId(String produkt) {
//
//		for (AbrechnungPosition pos : abrechnungPositionListe) {
//			if (pos.getProdukt().getTypCode().equals(produktTypCode)) {
//				return pos;
//			}
//		}
//
//		return null;
	

}
