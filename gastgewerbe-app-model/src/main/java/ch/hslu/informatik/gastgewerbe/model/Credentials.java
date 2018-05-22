package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "Credentials.findByBenutzername", query = "SELECT e FROM Credentials e WHERE e.benutzername=:benutzername") })
public class Credentials implements Serializable {

	private static final long serialVersionUID = -585087541600298206L;

	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String benutzername;
	private String passwort;

	public Credentials() {

	}

	public Credentials(String benutzername, String passwort) {
		this.benutzername = benutzername;
		this.passwort = passwort;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Credentials))
			return false;
		Credentials that = (Credentials) o;
		return id == that.id && Objects.equals(benutzername, that.benutzername)
				&& Objects.equals(passwort, that.passwort);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, benutzername, passwort);
	}

	@Override
	public String toString() {
		return "Credentials [id=" + id + ", benutzername=" + benutzername + ", passwort=" + passwort + "]";
	}

}
