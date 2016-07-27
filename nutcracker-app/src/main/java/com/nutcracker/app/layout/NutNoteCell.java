package com.nutcracker.app.layout;

import java.time.LocalDate;

import com.nutcracker.model.NutNote;

import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NutNoteCell extends AbstractCell<NutNote> {
	
	private final GridPane grid;
	private final Label iconLabel;
	
	private final Label titleHeaderLabel;
	private final Label titleLabel;
	
	private final Label ownerHeaderLabel;
	private final Label ownerLabel;
	
	private final Label categoryHeaderLabel;
	private final Label categoryLabel;
	
	private final Label deadlineLabel;
	
	{
		this.grid = new GridPane();
		this.iconLabel = new Label();
		this.titleHeaderLabel = new Label();
		this.titleLabel = new Label();
		this.ownerHeaderLabel = new Label();
		this.ownerLabel = new Label();
		this.categoryHeaderLabel = new Label();
		this.categoryLabel = new Label();
		this.deadlineLabel = new Label();
	}
	
	public NutNoteCell() {
		configureGrid();
		configureLabelsPositions();
		configureHeaderLabels();
	}
	
	@Override
	protected void addContent(NutNote note) {
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		
		this.titleLabel.setText(note.getNoteTitle());
		this.ownerLabel.setText(note.getNoteOwner().getUserLogin());
		this.categoryLabel.setText(note.getNoteCategory().getCategoryName());
		
		LocalDate noteDeadline = note.getNoteDeadline();
		String deadlineText = "";
		if (null != noteDeadline) {
			deadlineText = noteDeadline.toString();
		}
		this.deadlineLabel.setText(deadlineText);
		
		setGraphic(this.grid);
	}
	
	private void configureGrid() {
		this.grid.setHgap(15);
		this.grid.setVgap(4);
		this.grid.setPadding(new Insets(3, 10, 3, 10));
	}
	
	private void configureLabelsPositions() {
		this.grid.add(this.iconLabel, 0, 0, 1, 3);
		this.grid.add(this.deadlineLabel, 1, 0, 1, 3);
		this.grid.add(this.titleHeaderLabel, 2, 0);
		this.grid.add(this.titleLabel, 3, 0);
		this.grid.add(this.ownerHeaderLabel, 2, 1);
		this.grid.add(this.ownerLabel, 3, 1);
		this.grid.add(this.categoryHeaderLabel, 2, 2);
		this.grid.add(this.categoryLabel, 3, 2);
	}
	
	private void configureHeaderLabels() {
		this.titleHeaderLabel.setText("Title:");
		this.titleHeaderLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
		this.ownerHeaderLabel.setText("Owner:");
		this.ownerHeaderLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
		this.categoryHeaderLabel.setText("Category:");
		this.categoryHeaderLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
	}
}
