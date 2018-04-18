package ch.hslu.informatik.gastgewerbe.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Tisch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6183963575385026738L;
	
	@Id
	@GeneratedValue
	private long id;

}
