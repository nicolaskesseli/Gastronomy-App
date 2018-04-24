package ch.hslu.informatik.gastgewerbe.model;

public enum KategorieTyp {
    SNACK("Snack"), SPEISE("Speise"), GETRANK("Getränk");

    private String bezeichnung;

    private KategorieTyp(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String bezeichnung() {
        return bezeichnung;
    }

    public String toString() {
        return bezeichnung;
    }

}
