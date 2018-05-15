package ch.hslu.informatik.gastgewerbe.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
@NamedQueries({
		@NamedQuery(name = "Benutzer.findByBenutzername", query = "SELECT e FROM Benutzer e WHERE e.credentials.benutzername=:benutzername"),
		@NamedQuery(name = "Benutzer.findByRolleTyp", query = "SELECT e FROM Benutzer e WHERE e.rolle=:rolleTyp"), 
		@NamedQuery(name = "Benutzer.findAll", query = "SELECT e FROM Benutzer e"),
		@NamedQuery(name = "Benutzer.findByNachnameUndVorname", query = "SELECT e FROM Benutzer e WHERE e.nachname=:nachname AND e.vorname=:vorname"),
})

public class Benutzer extends Person {
	

	private static final long serialVersionUID = 2929748139540837451L;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Credentials credentials;

	@Enumerated(EnumType.STRING)
	private RolleTyp rolle;

	public Benutzer() {

	}

	public Benutzer(String nachname, String vorname, Adresse adresse, Kontakt kontakt, Credentials credentials,
			RolleTyp rolle) {
		super(nachname, vorname, adresse, kontakt);
		this.credentials = credentials;
		this.rolle = rolle;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public RolleTyp getRolle() {
		return rolle;
	}

	public void setRolle(RolleTyp rolle) {
		this.rolle = rolle;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Benutzer)) return false;
		if (!super.equals(o)) return false;
		Benutzer benutzer = (Benutzer) o;
		return Objects.equals(credentials, benutzer.credentials) &&
				rolle == benutzer.rolle;
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), credentials, rolle);
	}

	@Override
	public String toString() {
		return "Benutzer{" +
				"credentials=" + credentials +
				", rolle=" + rolle +
				'}';
	}
}

