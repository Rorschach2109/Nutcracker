package com.nutcracker.app.layout;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.nutcracker.model.NutNote;

import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class NutNoteCell extends AbstractCell<NutNote> {
	
	private final GridPane grid;
	private final Label iconLabel;
	private final Label titleLabel;
	private final Label ownerLabel;
	private final Label categoryLabel;
	private final Label deadlineLabel;
	
	{
		this.grid = new GridPane();
		this.iconLabel = new Label();
		this.titleLabel = new Label();
		this.ownerLabel = new Label();
		this.categoryLabel = new Label();
		this.deadlineLabel = new Label();
	}
	
	public NutNoteCell() {
		configureGrid();
		configureLabels();
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
	
	private void configureLabels() {
		this.grid.add(this.iconLabel, 0, 0, 1, 3);
		this.grid.add(this.titleLabel, 2, 0);
		this.grid.add(this.ownerLabel, 2, 1);
		this.grid.add(this.categoryLabel, 2, 2);
		this.grid.add(this.deadlineLabel, 1, 0, 1, 3);
	}
	
}
