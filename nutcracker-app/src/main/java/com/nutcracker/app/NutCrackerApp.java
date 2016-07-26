package com.nutcracker.app;

import com.nutcracker.app.controller.NutAppController;

import javafx.application.Application;
import javafx.stage.Stage;

public class NutCrackerApp extends Application {

	private final NutAppController nutAppController;
	
	{
		this.nutAppController = new NutAppController();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		if (false == nutAppController.initialize()) {
			return;
		}
		
		nutAppController.setStage(primaryStage);
		nutAppController.start();
	}
}
