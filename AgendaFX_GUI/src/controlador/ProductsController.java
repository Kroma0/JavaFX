package controlador;

import java.io.IOException;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Products;
import model.ProductsDAO;

public class ProductsController {

	private ProductsDAO products;
	
	private Stage ventana;
	@FXML private TextField idField;
	@FXML private TextField nameField;
	@FXML private TextField priceField;
	@FXML private TextField stockField;
	@FXML private DatePicker startCatalogueField;
	@FXML private DatePicker endingCatalogueField;
	@FXML private CheckBox packCheck;
	@FXML private GridPane packInputs;
	
	private ValidationSupport vs;
	private boolean pack = false;
	@FXML private void initialize() {
		//Obrir el fitxer de persones
		products = new ProductsDAO();
		products.openAll();
		//idField.setVisible(isDatosValidos());
		//Validació dades
		//https://github.com/controlsfx/controlsfx/issues/1148
		//produeix error si no posem a les VM arguments això: --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED
		vs = new ValidationSupport();
		vs.registerValidator(idField, true, Validator.createEmptyValidator("ID obligatori"));
		vs.registerValidator(nameField, true, Validator.createEmptyValidator("Nom obligatori"));
		vs.registerValidator(priceField, true, Validator.createEmptyValidator("Preu obligatori"));
		vs.registerValidator(stockField, true, Validator.createEmptyValidator("Stock obligatori"));
		vs.registerValidator(startCatalogueField, true, Validator.createEmptyValidator("Inici de cataleg obligatori"));
		vs.registerValidator(endingCatalogueField, true, Validator.createEmptyValidator("Fi de cataleg obligatori"));

	}
	
	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}
	
	@FXML private void onActionGuardar(ActionEvent e) throws IOException {
		//verificar si les dades són vàlides				
		if(isDatosValidos()){
			Products p = new Products(Integer.parseInt(idField.getText()), nameField.getText(), Integer.parseInt(priceField.getText()), Integer.parseInt(stockField.getText()),startCatalogueField.getValue(),endingCatalogueField.getValue());

			products.save(p);
			limpiarFormulario();
			products.showAll();
			System.out.println(p.toString());
		}
	}
	
	@FXML private void onActionListar(ActionEvent e) throws IOException {
		//verificar si les dades són vàlides				
		System.out.println(products.getMap().toString());
	}

	@FXML private void onActionEliminar(ActionEvent e) throws IOException {
			if(products.delete(Integer.parseInt(idField.getText()))){
				limpiarFormulario();
				products.showAll();
			}
	}

	@FXML private void onActionSortir(ActionEvent e) throws IOException {

		sortir();
		
		ventana.close();
	}

	public void sortir(){
		products.saveAll();
		products.showAll();
	}
	
	private boolean isDatosValidos() {

		//Comprovar si totes les dades són vàlides
		if (vs.isInvalid()) {
			String errors = vs.getValidationResult().getMessages().toString();
			// Mostrar finestra amb els errors
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(ventana);
			alert.setTitle("Camps incorrectes");
			alert.setHeaderText("Corregeix els camps incorrectes");
			alert.setContentText(errors);
			alert.showAndWait();
		
			return false;
		}

		return true;

	}
	
	private void limpiarFormulario(){
		idField.setText("");
		nameField.setText("");
		priceField.setText("");
		stockField.setText("");
		startCatalogueField.setValue(null);
		endingCatalogueField.setValue(null);
	}
	
	@FXML
	private void showPack(){
		
		if(!pack) {
			packInputs.setVisible(true);
			pack = true;
		} else {
			packInputs.setVisible(false);
			pack = false;
		}
		//idField.setVisible(isDatosValidos());
	}
}
