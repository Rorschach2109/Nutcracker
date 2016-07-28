package com.nutcracker.app.layout;

import java.util.function.Consumer;

import com.nutcracker.model.NutCategory;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NutCategoryCell extends AbstractCell<NutCategory> {

	private NutCategory category;
	
	private final GridPane grid;
	private final Label iconLabel;
	
	private final Label titleHeaderLabel;
	private final Label titleLabel;
	
	private final Label countHeaderLabel;
	private final Label countLabel;
	
	private final Button editButton;
	
	{
		this.grid = new GridPane();
		this.iconLabel = new Label();
		this.titleHeaderLabel = new Label();
		this.titleLabel = new Label();
		this.countHeaderLabel = new Label();
		this.countLabel = new Label();
		this.editButton = new Button();
	}
	
	public NutCategoryCell(double width, double height, Consumer<NutCategory> editButtonHandler) {
		configureGrid(width, height);
		configureComponentsPosition();
		configureHeaderLabels();
		configureButtons(editButtonHandler);
	}
	
	@Override
	protected void addContent(NutCategory category) {
		this.category = category;
		
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		
		this.titleLabel.setText(category.getCategoryName());
		this.countLabel.setText(Integer.toString(category.getCategoryNotes().size()));
		setGraphic(this.grid);
	}
	
	private void configureGrid(double width, double height) {
		this.grid.setHgap(10);
		this.grid.setVgap(4);
		this.grid.setPadding(new Insets(1, 5, 1, 5));
		this.grid.setMaxSize(width, height);
		setColumnConstraints();
	}
	
	private void setColumnConstraints() {
		ColumnConstraints columnA = new ColumnConstraints();
		columnA.setPercentWidth(8);

		ColumnConstraints columnB = new ColumnConstraints();
		columnB.setPercentWidth(20);
		
		ColumnConstraints columnC = new ColumnConstraints();
		columnC.setPercentWidth(60);
		
		ColumnConstraints columnD = new ColumnConstraints();
		columnD.setPercentWidth(10);
		
		this.grid.getColumnConstraints().addAll(columnA, columnB, 
				columnC, columnD);
	}
	
	private void configureComponentsPosition() {
		this.grid.add(this.iconLabel, 0, 0, 1, 2);
		this.grid.add(this.titleHeaderLabel, 1, 0);
		this.grid.add(this.titleLabel, 2, 0);
		this.grid.add(this.countHeaderLabel, 1, 1);
		this.grid.add(this.countLabel, 2, 1);
		this.grid.add(this.editButton, 3, 0, 1, 2);
	}
	
	private void configureHeaderLabels() {
		this.titleHeaderLabel.setText("Category name:");
		this.titleHeaderLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
		this.countHeaderLabel.setText("Notes count:");
		this.countHeaderLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
	}
	
	private void configureButtons(Consumer<NutCategory> editButtonHandler) {
		this.editButton.setText("Edit");
		this.editButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				editButtonHandler.accept(category);
			}
		});
	}
}
