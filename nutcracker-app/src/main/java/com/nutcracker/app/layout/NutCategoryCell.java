package com.nutcracker.app.layout;

import com.nutcracker.model.NutCategory;

import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class NutCategoryCell extends AbstractCell<NutCategory> {

	private final GridPane grid;
	private final Label iconLabel;
	private final Label titleLabel;
	
	{
		this.grid = new GridPane();
		this.iconLabel = new Label();
		this.titleLabel = new Label();
	}
	
	public NutCategoryCell() {
		configureGrid();
		configureLabels();
	}
	
	@Override
	protected void addContent(NutCategory category) {
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		
		String categoryName = category.getCategoryName();
		if (null == categoryName) {
			categoryName = "GLOBAL AVAILABLE";
		}
		
		this.titleLabel.setText(categoryName);
		setGraphic(this.grid);
	}
	
	private void configureGrid() {
		this.grid.setHgap(15);
		this.grid.setVgap(4);
		this.grid.setPadding(new Insets(3, 10, 3, 10));
	}
	
	private void configureLabels() {
		this.grid.add(this.iconLabel, 0, 0);
		this.grid.add(this.titleLabel, 1, 0);
	}
}
