package ch.hslu.informatik.gastgewerbe.model;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
		@NamedQuery(name = "Person.findByNachname", query = "SELECT e FROM Person e WHERE e.nachname=:nachname"),
		@NamedQuery(name = "Person.findByVorname", query = "SELECT e FROM Person e WHERE e.vorname=:vorname"),
		@NamedQuery(name = "Person.findByNachnameUndVorname", query = "SELECT e FROM Person e WHERE e.nachname=:nachname AND e.vorname=:vorname"),
		@NamedQuery(name = "Person.findByEmail", query = "SELECT e FROM Person e WHERE e.kontakt.email=:email") })
public class Person implements Serializable {

	private static final long serialVersionUID = 711951573552511601L;

	@Id
	@GeneratedValue
	private long id;

	private String nachname;
	private String vorname;
	private Adresse adresse;
	private Kontakt kontakt;

	public Person() {

	}

	public Person(String nachname, String vorname, Adresse adresse, Kontakt kontakt) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.adresse = adresse;
		this.kontakt = kontakt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Kontakt getKontakt() {
		return kontakt;
	}

	public void setKontakt(Kontakt kontakt) {
		this.kontakt = kontakt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person)) return false;
		Person person = (Person) o;
		return id == person.id &&
				Objects.equals(nachname, person.nachname) &&
				Objects.equals(vorname, person.vorname) &&
				Objects.equals(adresse, person.adresse) &&
				Objects.equals(kontakt, person.kontakt);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, nachname, vorname, adresse, kontakt);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", nachname=" + nachname + ", vorname=" + vorname + ", adresse=" + adresse
				+ ", kontakt=" + kontakt + "]";
	}
}
