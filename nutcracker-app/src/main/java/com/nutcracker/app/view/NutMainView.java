package com.nutcracker.app.view;

import com.nutcracker.app.controller.INutController;
import com.nutcracker.app.controller.NutMainController;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class NutMainView implements INutView {

	private NutMainController mainController;
	
	@FXML
	private AnchorPane contentPane;
	
	public <T> void changeLayoutList(ListView<T> layoutList) {
		if (null == layoutList) {
			return;
		}
		
		this.contentPane.getChildren().clear();
		layoutList.setMinSize(this.contentPane.getWidth(), this.contentPane.getHeight());
		this.contentPane.getChildren().add(layoutList);
	}
	
	@Override
	public void setController(INutController controller) {
		this.mainController = (NutMainController) controller;
		this.mainController.setView(this);
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleFutureButtonReleased() {
		this.mainController.generateFutureContent();
	}
	
	@FXML
	private void handlePastButtonReleased() {
		this.mainController.generatePastContent();
	}
	
	@FXML
	private void handleNotesButtonReleased() {
		this.mainController.generateNotesContent();
	}
	
	@FXML
	private void handleCategoriesButtonReleased() {
		this.mainController.generateCategoriesContent();
	}
	
	@FXML
	private void handleAddFutureButtonReleased() {
		this.mainController.showAddFutureWindow();
	}
	
	@FXML
	private void handleAddNoteButtonReleased() {
		this.mainController.showAddNoteWindow();
	}
	
	@FXML
	private void handleAddCategoryButtonReleased() {
		this.mainController.showAddCategoryWindow();
	}
}
