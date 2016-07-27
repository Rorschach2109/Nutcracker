package com.nutcracker.app.view;

import java.util.List;

import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NutNoteAddView extends AbstractNutNoteDetailsView {

	@FXML
	private Pane noteAddPane;
	@FXML
	private TextField titleField;
	@FXML
	private ComboBox<NutCategory> categoryChoiceBox;
	@FXML
	private CheckBox globalCheckBox;
	@FXML
	private TextArea messageField;
	@FXML
	private Label errorLabel;
	
	@Override
	public void insertCategories(List<NutCategory> categories) {
		this.categoryChoiceBox.setItems(FXCollections.observableArrayList(categories));
	}
	
	@Override
	public void showErrorMessage(String errorMessage) {
		this.errorLabel.setText(errorMessage);
		this.errorLabel.setVisible(true);
	}

	@Override
	protected Stage getViewStage() {
		return (Stage) this.noteAddPane.getScene().getWindow();
	}
	
	private NutNote createNote() {
		NutNote note = new NutNote(
				this.titleField.getText(),
				this.messageField.getText(),
				this.globalCheckBox.isSelected());
		note.setNoteCategory(this.categoryChoiceBox.getValue());
		
		return note;
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleAddCategoryButtonReleased() {
		this.noteDetailsController.showAddCategoryWindow();
	}
	
	@FXML
	private void handleCancelButtonReleased() {
		this.noteDetailsController.closeStage();
	}
	
	@FXML
	private void handleCreateButtonReleased() {
		NutNote note = createNote();
		this.noteDetailsController.handleConfirmButton(note);
	}
}
