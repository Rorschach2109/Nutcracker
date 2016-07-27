package com.nutcracker.app.controller;

import com.nutcracker.app.layout.StageManager;
import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.ResourcePathFinder;
import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;

import javafx.application.Platform;
import javafx.stage.Stage;

public class NutAppController implements INutController {

	private int currentUserId;
	private String currentUserLogin;
	
	private NutRemoteProxy nutRemoteProxy;
	private StageManager stageManager;
	
	private final NutMainController mainController;

	{
		this.nutRemoteProxy = new NutRemoteProxy();
		this.stageManager = new StageManager();
		this.mainController = new NutMainController(this, this.nutRemoteProxy);
	}
	
	public boolean initialize() {
		if (false == nutRemoteProxy.initialize()) {
			return false;
		}
		
		return true;
	}
	
	public void setStage(Stage stage) {
		this.stageManager.setStage(stage);
	}
	
	public void start() {
		INutController viewController = new NutLoginController(this, nutRemoteProxy);
		changeStage(ResourcePathFinder.LOGIN_VIEW, viewController);
	}
	
	public void enterMainWindow() {
		changeStage(ResourcePathFinder.MAIN_VIEW, this.mainController);
	}
	
	public void showAddFutureWindow() {
		INutController viewController = new NutReminderAddController(this, nutRemoteProxy);
		showWindow(ResourcePathFinder.REMINDER_DETAILS_VIEW, viewController, 
				this.mainController);
	}
	
	public void showAddNoteWindow(INutController parentController) {
		INutController viewController = new NutNoteAddController(this, nutRemoteProxy);
		showWindow(ResourcePathFinder.NOTE_DETAILS_VIEW, viewController, this.mainController);
	}
	
	public void showAddCategoryWindow(INutController parentController) {
		showAddCategoryWindow(parentController, null);
	}
	
	public void showAddCategoryWindow(INutController parentController, Stage parentStage) {
		INutController viewController = new NutCategoryAddController(this, nutRemoteProxy);
		showWindow(ResourcePathFinder.CATEGORY_DETAILS_VIEW, viewController, 
				parentController, parentStage);
	}
	
	public void showNoteDetailsWindow(NutNote note, INutController parentController) {
		
	}
	
	public void showReminderDetailsWindow(NutNote note, INutController parentController) {
		
	}
	
	public void showCategoryDetailsWindow(NutCategory category, INutController parentController) {
		
	}
	
	public INutView showNewStage(String sceneResourcePath) {
		return this.stageManager.showNewStage(sceneResourcePath);
	}
	
	public void updateMainView() {
		this.mainController.updateLayoutList();
	}
	
	public void close() {
		Platform.exit();
		System.exit(0);
	}
	
	public int getCurrentUserId() {
		return this.currentUserId;
	}
	
	public void setCurrentUserId(int currentUserId) {
		this.currentUserId = currentUserId;
	}
	
	public String getCurrentUserLogin() {
		return this.currentUserLogin;
	}
	
	public void setCurrentUserLogin(String currentUserLogin) {
		this.currentUserLogin = currentUserLogin;
	}
	
	private void changeStage(String sceneResourcePath, INutController viewController) {
		this.stageManager.changeStage(sceneResourcePath);
		INutView view = this.stageManager.getCurrentView();
		view.setController(viewController);
	}
	
	private void showWindow(String sceneResourcePath, INutController viewController, 
			INutController parentController) {
		showWindow(sceneResourcePath, viewController, parentController, null);
	}

	private void showWindow(String sceneResourcePath, INutController viewController, 
			INutController parentController, Stage parentStage) {
		INutView view = this.stageManager.showNewStage(sceneResourcePath, parentStage);
		viewController.setParentController(parentController);
		view.setController(viewController);
	}
}
