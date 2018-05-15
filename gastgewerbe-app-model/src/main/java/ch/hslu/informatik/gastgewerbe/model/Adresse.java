package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse implements Serializable {

	private static final long serialVersionUID = -466703289533993557L;
	
	private String strasse;
	private int plz;
	private String ort;

	public Adresse() {

	}

	public Adresse(String strasse, int plz, String name) {
		this.strasse = strasse;
		this.plz = plz;
		this.ort = name;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Adresse)) return false;
		Adresse adresse = (Adresse) o;
		return plz == adresse.plz &&
				Objects.equals(strasse, adresse.strasse) &&
				Objects.equals(ort, adresse.ort);
	}

	@Override
	public int hashCode() {

		return Objects.hash(strasse, plz, ort);
	}

	@Override
	public String toString() {
		return "Adresse [strasse=" + strasse + ", plz=" + plz + ", ort=" + ort + "]";
	}

}

