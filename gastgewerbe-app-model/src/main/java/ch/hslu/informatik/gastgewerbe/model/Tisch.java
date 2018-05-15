package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "Tisch.findByTischNr", query = "SELECT e FROM Tisch e WHERE e.tischNr=:tischNr")})

public class Tisch implements Serializable {


	private static final long serialVersionUID = -6183963575385026738L;
	
	@Id
	@GeneratedValue
	private long id;

	private int tischNr;
	

	public Tisch(int tischNr) {
		super();
		this.tischNr = tischNr;
	}
	
	public Tisch() {
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTischNr() {
		return tischNr;
	}

	public void setTischNr(int tischNr) {
		this.tischNr = tischNr;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Tisch)) return false;
		Tisch tisch = (Tisch) o;
		return id == tisch.id &&
				tischNr == tisch.tischNr;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, tischNr);
	}

	@Override
	public String toString() {
		return "Tisch{" +
				"id=" + id +
				", tischNr=" + tischNr +
				'}';
	}
}
