package com.nutcracker.app.view;

import com.nutcracker.app.controller.INutController;
import com.nutcracker.app.controller.NutNoteDetailsController;
import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NutNoteDetailsView implements INutView {

	private NutNoteDetailsController noteDetailsController;
	
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

	@Override
	public void setController(INutController controller) {
		this.noteDetailsController = (NutNoteDetailsController) controller;
		this.noteDetailsController.setView(this);
	}
	
	private boolean validateNote() {
		return true;
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleAddCategoryButtonReleased() {
		
	}
	
	@FXML
	private void handleCancelButtonReleased() {
		
	}
	
	@FXML
	private void handleCreateButtonReleased() {
		if (false == validateNote()) {
			return;
		}
		
		NutNote note = new NutNote(
				this.titleField.getText(),
				this.messageField.getText(),
				this.globalCheckBox.isSelected());
		
		int categoryId = this.categoryChoiceBox.getValue().getCategoryId();
		int placeId = -1;
		
		this.noteDetailsController.createNewNote(note, categoryId, placeId);
	}
}
