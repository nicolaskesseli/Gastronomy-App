package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@NamedQueries({ @NamedQuery(name = "Produkt.findByName", query = "SELECT e FROM Produkt e WHERE e.name=:name"),
		@NamedQuery(name = "Produkt.FindByProduktCode", query = "SELECT e FROM Produkt e WHERE e.produktCode=:produktCode"),
		@NamedQuery(name = "Produkt.findByKategorie", query = "SELECT e FROM Produkt e WHERE e.kategorie=:kategorieTyp"),
		@NamedQuery(name = "Produkt.findAll", query = "SELECT e FROM Produkt e") })
public class Produkt implements Serializable {

	private static final long serialVersionUID = -6748457523208107826L;
	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String produktCode;
	@Enumerated(EnumType.STRING)
	private KategorieTyp kategorie;

	private String name;
	private String beschreibung;
	private double preis;

	public Produkt() {

	}

	public Produkt(String produktCode, String name, String beschreibung, double preis, KategorieTyp kategorie) {
		this.produktCode = produktCode;
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

	public String getProduktCode() {
		return produktCode;
	}

	public void setProduktCode(String produktCode) {
		this.produktCode = produktCode;
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

	public KategorieTyp getKategorie() {
		return kategorie;
	}

	public void setKategorie(KategorieTyp kategorie) {
		this.kategorie = kategorie;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Produkt))
			return false;
		Produkt produkt = (Produkt) o;
		return id == produkt.id && Double.compare(produkt.preis, preis) == 0
				&& Objects.equals(produktCode, produkt.produktCode) && kategorie == produkt.kategorie
				&& Objects.equals(name, produkt.name) && Objects.equals(beschreibung, produkt.beschreibung);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, produktCode, kategorie, name, beschreibung, preis);
	}

	@Override
	public String toString() {
		return "Produkt{" + "id=" + id + ", produktCode='" + produktCode + '\'' + ", kategorie=" + kategorie
				+ ", name='" + name + '\'' + ", beschreibung='" + beschreibung + '\'' + ", preis=" + preis + '}';
	}
}
