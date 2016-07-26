package com.nutcracker.app.controller;

import java.util.List;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.util.TextValidator;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.NutCategoryAddView;
import com.nutcracker.model.NutCategory;
import com.nutcracker.remote.NutcrackerGetterRemote;
import com.nutcracker.remote.NutcrackerSetterRemote;

import javafx.stage.Stage;

public class NutCategoryAddController extends AbstractNutController {

	private NutCategoryAddView categoryAddView;
	
	private Stage viewStage;
	
	private final NutAppController nutAppController;
	private final NutRemoteProxy remoteProxy;
	
	public NutCategoryAddController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		this.nutAppController = nutAppController;
		this.remoteProxy = remoteProxy;
	}
	
	public void setStage(Stage viewStage) {
		this.viewStage = viewStage;
	}
	
	public boolean validateCategory(NutCategory category) {
		if (TextValidator.isEmpty(category.getCategoryName())) {
			this.categoryAddView.showErrorMessage("Category name cannot be empty");
			return false;
		}
		
		if (true == categoryExists(category)) {
			this.categoryAddView.showErrorMessage("Category already exists");
			return false;
		}
		
		return true;
	}
	
	public void insertCategory(NutCategory category) {
		NutcrackerSetterRemote nutSetter = remoteProxy.getNutSetter();
		nutSetter.insertCategory(nutAppController.getCurrentUserId(), category);
		notifyParent();
	}
	
	public void closeStage() {
		this.viewStage.close();
	}
	
	@Override
	public void setView(INutView view) {
		this.categoryAddView = (NutCategoryAddView) view;
	}

	private boolean categoryExists(NutCategory category) {
		NutcrackerGetterRemote nutGetter = remoteProxy.getNutGetter();
		List<NutCategory> categories = nutGetter.getUserCategories(nutAppController.getCurrentUserId());
		return categories.contains(category);
	}
}
