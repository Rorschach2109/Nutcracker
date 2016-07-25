package com.nutcracker.app.controller;

import com.nutcracker.app.layout.StageManager;
import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.ResourcePathFinder;

import javafx.application.Platform;
import javafx.stage.Stage;

public class NutAppController implements INutController {

	private NutRemoteProxy nutRemoteProxy;
	private StageManager stageManager;
	
	private int currentUserId;
	private String currentUserLogin;

	{
		this.nutRemoteProxy = new NutRemoteProxy();
		this.stageManager = new StageManager();
	}
	
	public boolean initialize() {
		if (false == nutRemoteProxy.initialize()) {
			return false;
		}
		
		return true;
	}
	
	public void start(Stage stage) {
		this.stageManager.setStage(stage);
		this.stageManager.changeStage(ResourcePathFinder.LOGIN_VIEW);
		
		NutLoginController loginController = new NutLoginController(this, nutRemoteProxy);
		INutView view = this.stageManager.getCurrentView();
		view.setController(loginController);
	}
	
	public void enterMainWindow() {
		this.stageManager.changeStage(ResourcePathFinder.MAIN_VIEW);
		
		NutMainController mainController = new NutMainController(this, nutRemoteProxy);
		INutView view = this.stageManager.getCurrentView();
		view.setController(mainController);
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
}
