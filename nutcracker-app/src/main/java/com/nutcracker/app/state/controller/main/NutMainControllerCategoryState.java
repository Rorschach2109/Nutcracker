package com.nutcracker.app.state.controller.main;

import com.nutcracker.app.controller.NutMainController;
import com.nutcracker.model.NutCategory;

public class NutMainControllerCategoryState implements INutMainControllerState {
	
	@Override
	public void updateLayoutList(NutMainController mainController) {
		mainController.generateCategoriesContent();
	}
	
	@Override
	public <T> void layoutListDetailsHandler(NutMainController mainController, T object) {
		mainController.showCategoryDetailsWindow((NutCategory) object);
	}
	
	@Override
	public <T> void layoutListEditButtonHandler(NutMainController mainController, T object) {
		mainController.showEditCategoryWindow((NutCategory) object);
	}
}
