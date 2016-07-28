package com.nutcracker.app.layout;

import com.nutcracker.model.NutCategory;

import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NutCategoryCell extends AbstractCell<NutCategory> {

	private final GridPane grid;
	private final Label iconLabel;
	
	private final Label titleHeaderLabel;
	private final Label titleLabel;
	
	private final Label countHeaderLabel;
	private final Label countLabel;
	
	{
		this.grid = new GridPane();
		this.iconLabel = new Label();
		this.titleHeaderLabel = new Label();
		this.titleLabel = new Label();
		this.countHeaderLabel = new Label();
		this.countLabel = new Label();
	}
	
	public NutCategoryCell(double width, double height) {
		configureGrid(width, height);
		configureLabelsPosition();
		configureHeaderLabels();
	}
	
	@Override
	protected void addContent(NutCategory category) {
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		
		this.titleLabel.setText(category.getCategoryName());
		this.countLabel.setText(Integer.toString(category.getCategoryNotes().size()));
		setGraphic(this.grid);
	}
	
	private void configureGrid(double width, double height) {
		this.grid.setHgap(15);
		this.grid.setVgap(4);
		this.grid.setPadding(new Insets(3, 10, 3, 10));
	}
	
	private void configureLabelsPosition() {
		this.grid.add(this.iconLabel, 0, 0, 1, 2);
		this.grid.add(this.titleHeaderLabel, 1, 0);
		this.grid.add(this.titleLabel, 2, 0);
		this.grid.add(this.countHeaderLabel, 1, 1);
		this.grid.add(this.countLabel, 2, 1);
	}
	
	private void configureHeaderLabels() {
		this.titleHeaderLabel.setText("Category name:");
		this.titleHeaderLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
		this.countHeaderLabel.setText("Notes count:");
		this.countHeaderLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
	}
}
