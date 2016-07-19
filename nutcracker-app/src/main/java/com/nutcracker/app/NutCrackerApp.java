package com.nutcracker.app;

import com.nutcracker.app.controller.NutController;

import javafx.application.Application;
import javafx.stage.Stage;

public class NutCrackerApp extends Application {

	private final NutController nutController;
	
	{
		this.nutController = new NutController();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		if (false == nutController.initialize()) {
			return;
		}
		
		nutController.start(primaryStage);
	}
}
