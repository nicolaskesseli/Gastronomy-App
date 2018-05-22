package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Kontakt implements Serializable {

	private static final long serialVersionUID = -3834643839871314740L;

	@Column(unique = true)
	private String email;
	private String telefon;

	public Kontakt() {

	}

	public Kontakt(String email, String telefon) {
		this.email = email;
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Kontakt)) return false;
		Kontakt kontakt = (Kontakt) o;
		return Objects.equals(email, kontakt.email) &&
				Objects.equals(telefon, kontakt.telefon);
	}

	@Override
	public int hashCode() {

		return Objects.hash(email, telefon);
	}

	@Override
	public String toString() {
		return "Kontakt{" +
				"email='" + email + '\'' +
				", telefon='" + telefon + '\'' +
				'}';
	}
}

