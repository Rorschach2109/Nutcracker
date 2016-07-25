package com.nutcracker.app.layout;

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
	
	{
		this.currentStage = null;
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
		FXMLLoader fxmlLoader = new FXMLLoader();
		Scene stageScene = loadScene(fxmlLoader, sceneResourcePath);
		
		this.setCurrentController(fxmlLoader);		
		
		this.currentStage.close();
		this.currentStage.setScene(stageScene);
		this.currentStage.setResizable(false);
		this.currentStage.show();
	}
	
	public INutView showNewStage(String sceneResourcePath) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		Scene stageScene = loadScene(fxmlLoader, sceneResourcePath);
		
		Stage newStage = new Stage();
		newStage.setScene(stageScene);
		newStage.setResizable(false);
		newStage.show();
		return fxmlLoader.getController();
	}
	
	private Scene loadScene(FXMLLoader fxmlLoader, String sceneResourcePath) {
		Scene scene = null;
		
		try {
			Pane pane = loadLayout(fxmlLoader, sceneResourcePath);
			scene = new Scene(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return scene;
	}
	
	private Pane loadLayout(FXMLLoader fxmlLoader, String sceneResourcePath) throws IOException {
		URL resourcePath = getClass().getClassLoader().getResource(sceneResourcePath);
		fxmlLoader.setLocation(resourcePath); 

		return fxmlLoader.load();
	}
	
	private void setCurrentController(FXMLLoader fxmlLoader) {
		this.currentView = fxmlLoader.getController();
	}
}
