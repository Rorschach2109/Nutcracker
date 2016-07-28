package com.nutcracker.app.view;

import java.util.List;

import com.nutcracker.app.controller.AbstractNutNoteDetailsController;
import com.nutcracker.app.controller.INutController;
import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;

import javafx.stage.Stage;

public abstract class AbstractNutNoteDetailsView implements INutView {

	protected AbstractNutNoteDetailsController noteDetailsController;
	
	public abstract void setContent(NutNote note);
	public abstract void insertCategories(List<NutCategory> categories);
	public abstract void showErrorMessage(String errorMessage);
	protected abstract Stage getViewStage();
	
	@Override
	public final void setController(INutController controller) {
		this.noteDetailsController = (AbstractNutNoteDetailsController) controller;
		this.noteDetailsController.setView(this);
		this.noteDetailsController.initialize();
		this.noteDetailsController.setStage(getViewStage());
	}
	
	protected void showAddCategrotyWindow() {
		this.noteDetailsController.showAddCategoryWindow();
	}
	
	protected void closeStage() {
		this.noteDetailsController.closeStage();
	}
}
