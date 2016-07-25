package com.nutcracker.app.view;

import java.util.List;

import com.nutcracker.app.controller.INutController;
import com.nutcracker.app.controller.NutNoteDetailsController;
import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NutNoteDetailsView implements INutView {

	private NutNoteDetailsController noteDetailsController;
	
	@FXML
	private Pane noteDetailsPane;
	@FXML
	private TextField titleField;
	@FXML
	private ChoiceBox<NutCategory> categoryChoiceBox;
	@FXML
	private DatePicker deadlinePicker;
	@FXML
	private CheckBox globalCheckBox;
	@FXML
	private TextArea messageField;
	@FXML
	private Label errorLabel;

	public void insertCategories(List<NutCategory> categories) {
		this.categoryChoiceBox.setItems(FXCollections.observableArrayList(categories));
	}
	
	@Override
	public void setController(INutController controller) {
		this.noteDetailsController = (NutNoteDetailsController) controller;
		this.noteDetailsController.setView(this);
		this.noteDetailsController.initialize();
		this.noteDetailsController.setStage((Stage) this.noteDetailsPane.getScene().getWindow());
	}
	
	public void showErrorMessage(String errorMessage) {
		this.errorLabel.setText(errorMessage);
		this.errorLabel.setVisible(true);
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
		NutNote note = new NutNote(
				this.titleField.getText(),
				this.messageField.getText(),
				this.globalCheckBox.isSelected());
		note.setNoteDeadline(this.deadlinePicker.getValue());
		note.setNoteCategory(this.categoryChoiceBox.getValue());
		
		if (false == this.noteDetailsController.validateNote(note)) {
			return;
		}
		
		int categoryId = note.getNoteCategory().getCategoryId();
		int placeId = -1;
		
		this.noteDetailsController.createNewNote(note, categoryId, placeId);
		this.noteDetailsController.closeStage();
	}
}
