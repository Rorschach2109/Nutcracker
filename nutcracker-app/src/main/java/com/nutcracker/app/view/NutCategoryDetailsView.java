package com.nutcracker.app.view;

import com.nutcracker.app.controller.INutController;
import com.nutcracker.app.controller.NutCategoryAddController;
import com.nutcracker.model.NutCategory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NutCategoryDetailsView implements INutView {

	private NutCategoryAddController categoryAddController;
	
	@FXML
	private Pane categoryAddPane;
	@FXML
	private TextField categoryNameField;
	@FXML
	private Label errorLabel;
	
	public void showErrorMessage(String errorMessage) {
		this.errorLabel.setText(errorMessage);
		this.errorLabel.setVisible(true);
	}
	
	public void setContent(NutCategory category) {
		this.categoryNameField.setText(category.getCategoryName());
	}
	
	@Override
	public void setController(INutController controller) {
		this.categoryAddController = (NutCategoryAddController) controller;
		this.categoryAddController.setView(this);
		this.categoryAddController.setStage((Stage) categoryAddPane.getScene().getWindow());
	}
	
	@FXML
	private void handleCancelButtonReleased() {
		this.categoryAddController.closeStage();
	}
	
	@FXML
	private void handleConfirmButtonReleased() {
		NutCategory category = new NutCategory(this.categoryNameField.getText());
		this.categoryAddController.handleConfirmButton(category);
	}
}
