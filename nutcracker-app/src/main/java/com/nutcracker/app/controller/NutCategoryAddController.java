package com.nutcracker.app.controller;

import java.util.List;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.util.TextValidator;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.NutCategoryDetailsView;
import com.nutcracker.model.NutCategory;
import com.nutcracker.remote.NutcrackerGetterRemote;
import com.nutcracker.remote.NutcrackerSetterRemote;
import com.nutcracker.remote.NutcrackerUpdaterRemote;

import javafx.stage.Stage;

public class NutCategoryAddController extends AbstractNutController {

	private int currentCategoryId;
	private boolean editMode;
	private NutCategoryDetailsView categoryAddView;
	
	private Stage viewStage;
	
	public NutCategoryAddController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		super(nutAppController, remoteProxy);
		this.currentCategoryId = 0;
		this.editMode = false;
	}
	
	public void setStage(Stage viewStage) {
		this.viewStage = viewStage;
	}
	
	public void setContent(NutCategory category) {
		if (null != category) {
			this.categoryAddView.setContent(category);
			this.currentCategoryId = category.getCategoryId();
			this.editMode = true;
		}
	}
	
	public boolean validateCategory(NutCategory category) {
		if (TextValidator.isEmpty(category.getCategoryName())) {
			this.categoryAddView.showErrorMessage("Category name cannot be empty");
			return false;
		}
		
		if (false == editMode && true == categoryExists(category)) {
			this.categoryAddView.showErrorMessage("Category already exists");
			return false;
		}
		
		return true;
	}
	
	public void handleConfirmButton(NutCategory category) {
		if (validateCategory(category)) {
			confirmButtonAction(category);
			notifyParent();
			closeStage();
		}
	}
	
	public void closeStage() {
		this.viewStage.close();
	}
	
	@Override
	public void setView(INutView view) {
		this.categoryAddView = (NutCategoryDetailsView) view;
	}
	
	private void confirmButtonAction(NutCategory category) {
		if (this.editMode) {
			category.setCategoryId(this.currentCategoryId);
			NutcrackerUpdaterRemote nutUpdater = remoteProxy.getNutUpdater();
			nutUpdater.updateCategory(nutAppController.getCurrentUserId(), category);
		} else {
			NutcrackerSetterRemote nutSetter = remoteProxy.getNutSetter();
			nutSetter.insertCategory(nutAppController.getCurrentUserId(), category);
		}
	}

	private boolean categoryExists(NutCategory category) {
		NutcrackerGetterRemote nutGetter = remoteProxy.getNutGetter();
		List<NutCategory> categories = nutGetter.getUserCategories(nutAppController.getCurrentUserId());
		return categories.contains(category);
	}
}
