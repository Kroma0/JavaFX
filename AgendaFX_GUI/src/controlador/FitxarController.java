package controlador;

import java.io.IOException;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Treballador;
import model.TreballadorDAO;

public class FitxarController {
	
	private TreballadorDAO treballador;
	
	private Stage ventana;
	@FXML private TextField idField;
	
	private ValidationSupport vs;
	
	@FXML private void initialize() {
		//Obrir el fitxer de persones
		treballador = new TreballadorDAO();
		treballador.openAll();
		//idField.setVisible(isDatosValidos());
		//Validació dades
		//https://github.com/controlsfx/controlsfx/issues/1148
		//produeix error si no posem a les VM arguments això: --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED
		vs = new ValidationSupport();
		vs.registerValidator(idField, true, Validator.createEmptyValidator("ID obligatori"));

	}
	
	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}
	
	@FXML private void onActionGuardarEntrada(ActionEvent e) throws IOException {
		//verificar si les dades són vàlides				
		if(isDatosValidos()){
			Treballador t = new Treballador(Integer.parseInt(idField.getText()),java.time.LocalTime.now(),null);

			treballador.save(t);
			limpiarFormulario();
			treballador.showAll();
			System.out.println(t.toString());
		}
	}
	
	@FXML private void onActionGuardarSalida(ActionEvent e) throws IOException {
		//verificar si les dades són vàlides				
		if(isDatosValidos()){
			Treballador t = new Treballador(Integer.parseInt(idField.getText()),java.time.LocalTime.now(),java.time.LocalTime.now());

			treballador.save(t);
			limpiarFormulario();
			treballador.showAll();
			System.out.println(t.toString());
		}
	}
	
	@FXML private void onActionListar(ActionEvent e) throws IOException {
		//verificar si les dades són vàlides				
		System.out.println(treballador.getMap().toString());
	}

	@FXML private void onActionEliminar(ActionEvent e) throws IOException {
			if(treballador.delete(Integer.parseInt(idField.getText()))){
				limpiarFormulario();
				treballador.showAll();
			}
	}

	@FXML private void onActionSortir(ActionEvent e) throws IOException {

		sortir();
		
		ventana.close();
	}

	public void sortir(){
		treballador.saveAll();
		treballador.showAll();
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
	}
}
