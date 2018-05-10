package ch.hslu.informatik.gastgewerbe.gui.kellner;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Table {

	private final SimpleStringProperty rBezeichnung;
	private final SimpleDoubleProperty rPreis;
	
	public Table(String sBezeichnung, double sPreis) {
		this.rBezeichnung = new SimpleStringProperty(sBezeichnung);
		this.rPreis = new SimpleDoubleProperty(sPreis);
		
	}
	
	public String getRBezeichung() {
		return rBezeichnung.get();
	}
	
	public void setRBezeichnung(String b) {
		rBezeichnung.set(b);
	}
	
	public Double getRPreis() {
		return rPreis.get();
	}
	
	public void setRPreis(Double p) {
		rPreis.set(p);
	}
}
