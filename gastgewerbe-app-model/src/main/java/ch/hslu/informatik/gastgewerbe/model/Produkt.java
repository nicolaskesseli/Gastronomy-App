package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({ @NamedQuery(name = "ProduktTyp.findByName", query = "SELECT e FROM ProduktTyp e WHERE e.name=:name"),
		@NamedQuery(name = "ProduktTyp.findByTypCode", query = "SELECT e FROM ProduktTyp e WHERE e.typCode=:typCode"),
		@NamedQuery(name = "ProduktTyp.findByLieferant", query = "SELECT e FROM ProduktTyp e WHERE e.lieferant=:lieferant") })
public class Produkt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6748457523208107826L;
	@Id
	@GeneratedValue
	private long id;
	private int produktId;
	private String name;
	private String beschreibung;
	private double preis;
	private String kategorie;
	
	


	public Produkt() {

	}

	public Produkt(int produktId, String name, String beschreibung, double preis,
			String kategorie) {
		this.produktId = produktId;
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.kategorie = kategorie;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getProduktId() {
		return produktId;
	}

	public void setProduktId(int produktId) {
		this.produktId = produktId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}


	public String getKategorie() {
		return kategorie;
	}
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;	
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
		Produkt other = (Produkt) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return name;
	}
}
