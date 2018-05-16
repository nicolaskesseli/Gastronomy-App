package ch.hslu.informatik.gastgewerbe.model;


public enum RolleTyp {
	KELLNER("Kellner"), KUECHE_MITARBEITER("Kueche-Mitarbeiter"), BAR_MITARBEITER(
			"Bar-Mitarbeiter"), ADMINISTRATOR("Administrator");

	private String bezeichnung;

	private RolleTyp(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String bezeichnung() {
		return bezeichnung;
	}

	public String toString() {
		return bezeichnung;
	}
}
