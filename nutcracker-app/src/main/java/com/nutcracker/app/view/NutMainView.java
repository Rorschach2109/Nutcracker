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
	private void handleRemindersButtonReleased() {
		this.mainController.generateRemindersContent();
	}
	
	@FXML
	private void handleNotesButtonReleased() {
		this.mainController.generateNotesContent();
	}
	
	@FXML
	private void handleCategoriesButtonReleased() {
		
	}
}
