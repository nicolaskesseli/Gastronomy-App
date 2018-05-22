package ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper;

import ch.hslu.informatik.gastgewerbe.model.Bestellung;

import java.time.LocalDateTime;

public class BestellungWrapper {

	private Bestellung bestellung;

	public BestellungWrapper() {

	}

	public BestellungWrapper(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	public Bestellung getBestellung() {
		return bestellung;
	}

	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	public long getId() {
		return bestellung.getId();
	}

	public void setId(long id) {
		bestellung.setId(id);
	}

	public void setTischNr(int tischNr) {
		bestellung.getTisch().setTischNr(tischNr);
	}

	public int getTischNr() {
		return bestellung.getTisch().getTischNr();
	}

	public void setZeit(LocalDateTime zeit) {
		bestellung.setZeit(zeit);
	}

	public LocalDateTime getZeit() {
		return bestellung.getZeit();
	}

	public String getBemerkung() {

		return bestellung.getBemerkung();
	}

	public void setBemerkung(String bemerkung) {

		bestellung.setBemerkung(bemerkung);
	}
}
