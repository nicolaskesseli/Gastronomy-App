package ch.hslu.informatik.gastgewerbe.gui.verwaltung;
/*


import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.cell.CheckBoxTableCell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper.AbrechnungWrapper;
import ch.hslu.informatik.gastgewerbe.model.Abrechnung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class AbrechnungViewController {

	private static Logger logger = LogManager.getLogger(AbrechnungViewController.class);
	
	private List<AbrechnungWrapper> abrechnungWrapperList= new ArrayList<>();
	
	private List<Abrechnung> abrechnungList= new ArrayList<>();

	
    @FXML
    private TextField datumInput;

    @FXML
    private Button abrechnungenSuchenBtn;

    @FXML
    private TableView<AbrechnungWrapper> tblUebersichtBestellung;

    @FXML
    private TableColumn<AbrechnungWrapper, String> colBenutzer;

    @FXML
    private TableColumn<AbrechnungWrapper, LocalDateTime> colZeit;

    @FXML
    private TableColumn<AbrechnungWrapper, Double> colGesamtbetrag;

    @FXML
    private TableColumn<AbrechnungWrapper, String> colStatus;

    @FXML
    private Button tagesAbrechnungAbschliessenBtn;

    @FXML
    private TableColumn<AbrechnungWrapper, String> colAusgewaelt;

    @FXML
    private Label lblTotal;

    @FXML
    void abrechnungenSuchen(ActionEvent event) throws Exception{
    	
    	try {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD.MM.YYYY");

        String date = datumInput.getText();

        LocalDateTime zeit = LocalDateTime.parse(date, formatter);
        
        abrechnungList = Context.getInstance().getAbrechnungService().findByDatum(zeit);
        
        //TODO: Liste in Wrapper-Liste umwandeln, die dann der Tabelle hinzufügen und dann die gewünschten ergebnisse ausgeben.
    	

        double tagesUmsatz = Context.getInstance().getAbrechnungService().abschluss(zeit);
    
        lblTotal.setText(String.valueOf(tagesUmsatz));

    	
    }catch (Exception e){
		String msg = "Tagesabrechnung misslungen";
		logger.error(msg, e);
		throw new Exception(msg);
    	
    }
    }

    @FXML
    void tagesAbrechnungAbschliessen(ActionEvent event) {

    }

    @FXML
    void zurück(ActionEvent event) {

    }
    
    
    public void initialize(URL location, ResourceBundle resources) throws Exception {

		String benutzer = Context.getInstance().getBenutzer().getVorname() + " "
				+ Context.getInstance().getBenutzer().getNachname();
		

		//Zeit formatieren

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");

	    try{

	    	// Tabelle initialisieren
	    	colBenutzer.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper, String>("benutzer"));
			colZeit.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper, LocalDateTime>("zeit"));
			colGesamtbetrag.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper, Double>("gesamtbetrag"));
			colStatus.setCellValueFactory(new PropertyValueFactory<AbrechnungWrapper, String>("status"));
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


	    }catch (Exception e){
			String msg = "Tagesabrechnung misslungen";
			logger.error(msg, e);
			throw new Exception(msg);
	    	
	    
    }
    
}
}*/