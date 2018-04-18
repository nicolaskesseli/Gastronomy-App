package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@Temporal(TemporalType.TIMESTAMP)
	private GregorianCalendar zeit;

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

	public Abrechnung(GregorianCalendar zeit, Benutzer benutzer) {
		this.zeit = zeit;
		this.benutzer = benutzer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public GregorianCalendar getZeit() {
		return zeit;
	}

	public void setZeit(GregorianCalendar zeit) {
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
	public AbrechnungPosition findByProduktTyp(ProduktTyp produktTyp) {

		for (AbrechnungPosition pos : abrechnungPositionListe) {
			if (pos.getProduktTyp().equals(produktTyp)) {
				return pos;
			}
		}

		return null;
	}

	/**
	 * Liefert die Rechnungsposition für den übergebenen Produtktyp Code zurück.
	 *
	 * @param produktTypCode
	 * @return
	 */
	public AbrechnungPosition findByProduktTypCode(String produktTypCode) {

		for (AbrechnungPosition pos : abrechnungPositionListe) {
			if (pos.getProduktTyp().getTypCode().equals(produktTypCode)) {
				return pos;
			}
		}

		return null;
	}

}