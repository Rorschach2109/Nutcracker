package com.nutcracker.app.view;

import java.util.List;

import com.nutcracker.app.controller.INutController;
import com.nutcracker.app.controller.NutNoteAddController;
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

public class NutNoteAddView implements INutView {

	private NutNoteAddController noteAddController;
	
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
	
	public void insertCategories(List<NutCategory> categories) {
		this.categoryChoiceBox.setItems(FXCollections.observableArrayList(categories));
	}
	
	@Override
	public void setController(INutController controller) {
		this.noteAddController = (NutNoteAddController) controller;
		this.noteAddController.setView(this);
		this.noteAddController.initialize();
		this.noteAddController.setStage((Stage) this.noteAddPane.getScene().getWindow());
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
		this.noteAddController.showAddCategoryWindow();
	}
	
	@FXML
	private void handleCancelButtonReleased() {
		this.noteAddController.closeStage();
	}
	
	@FXML
	private void handleCreateButtonReleased() {
		NutNote note = new NutNote(
				this.titleField.getText(),
				this.messageField.getText(),
				this.globalCheckBox.isSelected());
		note.setNoteCategory(this.categoryChoiceBox.getValue());
		
		if (false == this.noteAddController.validateNote(note)) {
			return;
		}
		
		int categoryId = note.getNoteCategory().getCategoryId();
		
		this.noteAddController.createNewNote(note, categoryId);
		this.noteAddController.closeStage();
	}
}
