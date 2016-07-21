package com.nutcracker.app.view;

import java.util.List;

import com.nutcracker.app.controller.INutController;
import com.nutcracker.app.controller.NutMainController;
import com.nutcracker.app.layout.NutNoteCell;
import com.nutcracker.model.NutNote;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class NutMainView implements INutView {

	private NutMainController mainController;
	
	@FXML
	private AnchorPane contentPane;
	
	public void generateContent(List<NutNote> contentList, ListCell<NutNote> cellFactory) {
		System.out.println("generateContent");
		ListView<NutNote> layoutList = createLayoutList(contentList, cellFactory);
		changeLayoutList(layoutList);
	}
	
	@Override
	public void setController(INutController controller) {
		this.mainController = (NutMainController) controller;
		this.mainController.setView(this);
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void handleRemindersButtonReleased() {
		this.mainController.generateRemindersContent();
	}
	
	@FXML
	private void handleNotesButtonReleased() {
		this.mainController.generateNotesContent();
	}
	
	@FXML
	private void handleCategoriesButtonReleased() {
		
	}

	private ListView<NutNote> createLayoutList(List<NutNote> contentList, ListCell<NutNote> cellFactory) {
		ListView<NutNote> layoutList = new ListView<>();
		
		layoutList.setCellFactory(new Callback<ListView<NutNote>, ListCell<NutNote>>() {
            @Override public ListCell<NutNote> call(ListView<NutNote> list) {
                return new NutNoteCell();
            }
        });
		layoutList.setItems(FXCollections.observableArrayList(contentList));
		
		return layoutList;
	}
	
	private void changeLayoutList(ListView<NutNote> layoutList) {
		this.contentPane.getChildren().clear();
		layoutList.setMinSize(this.contentPane.getWidth(), this.contentPane.getHeight());
		this.contentPane.getChildren().add(layoutList);
	}
}
