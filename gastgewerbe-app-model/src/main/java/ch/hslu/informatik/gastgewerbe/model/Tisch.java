package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tisch implements Serializable {

	/**
	 * 
	 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + tischNr;
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
		Tisch other = (Tisch) obj;
		if (id != other.id)
			return false;
		if (tischNr != other.tischNr)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tisch [id=" + id + ", tischNr=" + tischNr + "]";
	}
}
