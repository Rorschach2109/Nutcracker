package com.nutcracker.app.util;

import java.io.IOException;
import java.net.URL;

import com.nutcracker.app.view.INutView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StageManager {

	private Stage currentStage;
	private INutView currentView;
	
	private FXMLLoader fxmlLoader;
	
	{
		this.currentStage = null;
		this.fxmlLoader = new FXMLLoader();
	}
	
	public void setStage(Stage stage) {
		this.currentStage = stage;
	}
	
	public INutView getCurrentView() {
		return this.currentView;
	}
	
	public void showCurrentStage() {
		this.currentStage.show();
	}
	
	public void changeStage(String sceneResourcePath) {
		Scene stageScene = loadScene(sceneResourcePath);
		
		this.setCurrentController();		
		
		this.currentStage.close();
		this.currentStage.setScene(stageScene);
		this.currentStage.show();
	}
	
	private Scene loadScene(String sceneResourcePath) {
		Scene scene = null;
		
		try {
			Pane pane = loadLayout(sceneResourcePath);
			scene = new Scene(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return scene;
	}
	
	private Pane loadLayout(String sceneResourcePath) throws IOException {
		URL resourcePath = getClass().getClassLoader().getResource(sceneResourcePath);
		this.fxmlLoader.setLocation(resourcePath); 
		
		return this.fxmlLoader.load();
	}
	
	private void setCurrentController() {
		this.currentView = this.fxmlLoader.getController();
	}
}
