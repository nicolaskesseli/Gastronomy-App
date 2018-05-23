package ch.hslu.informatik.gastgewerbe.gui.verwaltung;



import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ch.hslu.informatik.gastgewerbe.model.Benutzer;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper.AbrechnungWrapper;
import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class AbrechnungViewController implements Initializable {

	private static Logger logger = LogManager.getLogger(AbrechnungViewController.class);
	
	private List<AbrechnungWrapper> abrechnungWrapperList= new ArrayList<>();
	
	private List<Abrechnung> abrechnungList= new ArrayList<>();

    @FXML
    private TextField txtDatumInput;

    @FXML
    private TextField txtBenutzername;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblError;

    @FXML
    private TableView<AbrechnungWrapper> tblUebersichtBestellung;

    @FXML
    private TableColumn<AbrechnungWrapper, String> colBenutzer;

    @FXML
    private TableColumn<AbrechnungWrapper, LocalDateTime> colZeit;

    @FXML
    private TableColumn<AbrechnungWrapper, Double> colGesamtbetrag;

    @FXML
    private TableColumn<AbrechnungWrapper, Boolean> colStatus;

    @FXML
    private TableColumn<AbrechnungWrapper, Integer> colTischNr;

    @FXML
    private TableColumn<AbrechnungWrapper, Integer> colAnzPos;

    @FXML
    private TableColumn<AbrechnungWrapper, String> colAusgewaelt;

    @FXML
    private Button btnSuchen;

    @FXML
    private Button btnAlleAnzeigen;

    @FXML
    private Button btnAbschliessen;

    @FXML
    void abrechnungenSuchen(ActionEvent event) throws Exception{

    	try {
    	    lblError.setText("");
            lblTotal.setText("");

    	    tblUebersichtBestellung.getItems().clear();
    	    abrechnungList.clear();
    	    abrechnungWrapperList.clear();

    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss.SSS");



        String date = txtDatumInput.getText();
        date = date + " 00:00:00.001";

        LocalDateTime zeit = LocalDateTime.parse(date, formatter);

        String username = txtBenutzername.getText();

        Benutzer benutzer = Context.getInstance().getBenutzerService().findByBenutzername(username);

        logger.debug(zeit.toString());

        if(txtBenutzername.getText()!=null || !txtBenutzername.getText().trim().isEmpty()) {
            if(txtDatumInput.getText()!=null || !txtDatumInput.getText().trim().isEmpty()){
                List<Abrechnung> temp = Context.getInstance().getAbrechnungService().findByBenutzerUndDatum(benutzer, zeit);
                for(Abrechnung a : temp){
                    if(a.getBestellung().isRechnungBezahlt()){
                        abrechnungList.add(a);
                    }
                 }
            }
        }

        if(txtDatumInput.getText()!=null || !txtDatumInput.getText().trim().isEmpty()){
            if(txtBenutzername.getText() == null || txtBenutzername.getText().trim().isEmpty()) {
                List<Abrechnung> temp = Context.getInstance().getAbrechnungService().findByDatum(zeit);
                for(Abrechnung a : temp){
                    if (a.getBestellung().isRechnungBezahlt()){
                        abrechnungList.add(a);
                    }
                }
            }
        }

        logger.debug(abrechnungList.toString());

            for(Abrechnung a : abrechnungList ){
            abrechnungWrapperList.add(new AbrechnungWrapper(a));
            }

            tblUebersichtBestellung.getItems().addAll(abrechnungWrapperList);

        }catch (DateTimeParseException e){
    	    String msg = "Keine gültige Eingabe für Datum";
    	    logger.error(msg, e);
    	    txtDatumInput.setText("");
    	    txtDatumInput.setPromptText("dd.MM.yyyy");
    	    lblError.setText("Falsches Format");
        }catch (Exception e){
		    String msg = "Suche fehlgeschlagen: ";
		    logger.error(msg, e);
		    throw new Exception(msg + e);
        }
    }

    @FXML
    void tagesAbrechnungAbschliessen(ActionEvent event) throws Exception {

        Double gesamtBetragTagesAbrech;

        // Liste für ausgewählte Wrapper
        List<AbrechnungWrapper> ausgewaelt = new ArrayList<>();
        // Liste für Abrechnungen für den Abschluss
        List<Abrechnung> abschluss = new ArrayList<>();

        try {

            // Schleife für alle AbrechnungenWrapper die ausgewählt sind mit checkbox
            for (AbrechnungWrapper a : abrechnungWrapperList) {
                if (a.getAusgewaelt().isSelected()) {
                    ausgewaelt.add(a);
                }
            }

            if(!ausgewaelt.isEmpty()) {

                for (AbrechnungWrapper a : ausgewaelt) {
                    abschluss.add(a.getAbrechnung());
                }

                gesamtBetragTagesAbrech = Context.getInstance().getAbrechnungService().abschluss(abschluss);

                // Tabelle aktualisieren...
                for (AbrechnungWrapper a : ausgewaelt){
                    a.setTagesAbrechnung(true);
                }

                tblUebersichtBestellung.getItems().clear();
                tblUebersichtBestellung.getItems().addAll(ausgewaelt);

                lblTotal.setText(String.valueOf(gesamtBetragTagesAbrech)+"0 CHF");

            } else {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Info");
                info.setHeaderText("Abschluss nicht möglich");
                info.setContentText("Es wurden keine Abrechnungen ausgewählt!");
                info.showAndWait();
            }

        } catch (Exception e){
            String msg = "Tagesabschluss fehlgeschlagen: ";

            logger.error(msg, e);

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Fehler");
            error.setHeaderText("Fehler beim Abschluss");
            error.setContentText("Ein Fehler ist aufgetreten beim Abschluss. Bitte wenden Sie sich an Ihren Systemadministrator.");
            error.showAndWait();

            throw new Exception(msg + e);
        }
    }

    @FXML
    void alleAnzeigen(ActionEvent event) {

        updateTable();

    }

    
    public void initialize(URL location, ResourceBundle resources) {

//		String benutzer = Context.getInstance().getBenutzer().getVorname() + " "
//				+ Context.getInstance().getBenutzer().getNachname();

		//Tabelle editierbar machen

        tblUebersichtBestellung.setEditable(true);
		

		//Zeit formatieren

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");

	    try{

	    	// Tabelle initialisieren
	    	colBenutzer.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper, String>("benutzer"));
			colZeit.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper, LocalDateTime>("zeit"));
			colGesamtbetrag.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper, Double>("betrag"));
			colStatus.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper, Boolean>("tagesAbrechnung"));
			colAnzPos.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper,Integer>("bestellungPositionListe"));
			colTischNr.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper, Integer>("TischNr"));

            // Checkbox für ausgewählt
            colAusgewaelt.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper, String>("ausgewaelt"));


	        // Datumformat anpassen CellFactory anpassen um nach dateFormatter zu formatieren

			colZeit.setCellFactory(
					new Callback<TableColumn<AbrechnungWrapper, LocalDateTime>, TableCell<AbrechnungWrapper, LocalDateTime>>() {

						@Override
						public TableCell<AbrechnungWrapper, LocalDateTime> call(TableColumn<AbrechnungWrapper, LocalDateTime> param) {

							return new TableCell<AbrechnungWrapper, LocalDateTime>() {

								protected void updateItem(LocalDateTime item, boolean empty) {
									super.updateItem(item, empty);

									if (item == null || empty) {
										setText(null);
									} else {
										setText(dateFormatter.format(item));
									}
								}
							};
						}
					});

            /* Binding für die Schaltfläche 'Abschliessen' erstellen */
            btnAbschliessen.disableProperty().bind(Bindings.size(tblUebersichtBestellung.getItems()).lessThan(1));

            // Tabelle updaten
            updateTable();



        }catch (Exception e){
            logger.error("Fehler bei der View-Initialisierung: ", e);
            throw new RuntimeException(e);
        }
    
    }

    private void updateTable(){

        lblError.setText("");
        lblTotal.setText("");

        tblUebersichtBestellung.getItems().clear();
        abrechnungList.clear();
        abrechnungWrapperList.clear();

        try {
            List<Abrechnung> alle = Context.getInstance().getAbrechnungService().alleAbrechnungen();

            for(Abrechnung a : alle){
                if(a.getBestellung().isRechnungBezahlt()){
                    abrechnungList.add(a);
                }
            }

            for(Abrechnung a : abrechnungList ){
                abrechnungWrapperList.add(new AbrechnungWrapper(a));
            }

            tblUebersichtBestellung.getItems().addAll(abrechnungWrapperList);

        } catch (Exception e) {
            logger.error("Fehler beim updaten der Tabelle: ", e);
            throw new RuntimeException(e);
        }

    }

}