package ch.hslu.informatik.gastgewerbe.gui.verwaltung;



import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.hslu.informatik.gastgewerbe.gui.verwaltung.wrapper.AbrechnungWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class AbrechnungVerwaltungViewController {

	private static Logger logger = LogManager.getLogger(AbrechnungVerwaltungViewController.class);
	
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
    private Button zurückBtn;

    @FXML
    private Label lblTotal;

    @FXML
    void abrechnungenSuchen(ActionEvent event) throws Exception{
    	
    	try {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD.MM.YYYY");

        String date = datumInput.getText();

        LocalDateTime zeit = LocalDateTime.parse(date, formatter);
    	
    	

    double tagesUmsatz = Context.getInstance().getAbrechnungService().abschluss(zeit);
    	
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
}