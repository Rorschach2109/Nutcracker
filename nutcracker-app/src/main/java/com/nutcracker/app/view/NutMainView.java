package com.nutcracker.app.view;

import com.nutcracker.app.controller.INutController;
import com.nutcracker.app.controller.NutMainController;

import javafx.fxml.FXML;

public class NutMainView implements INutView {

	private NutMainController mainController;
	
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
		
	}
	
	@FXML
	private void handleNotesButtonReleased() {
		
	}
	
	@FXML
	private void handleCategoriesButtonReleased() {
		
	}

}
