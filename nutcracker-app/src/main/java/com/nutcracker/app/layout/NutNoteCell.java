package com.nutcracker.app.layout;

import java.time.LocalDate;
import java.util.function.Consumer;

import com.nutcracker.model.NutNote;

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

public class NutNoteCell extends AbstractCell<NutNote> {
	
	private final Consumer<NutNote> editButtonHandler;
	
	private final GridPane grid;
	private final Label iconLabel;
	
	private final Label titleHeaderLabel;
	private final Label titleLabel;
	
	private final Label ownerHeaderLabel;
	private final Label ownerLabel;
	
	private final Label categoryHeaderLabel;
	private final Label categoryLabel;
	
	private final Label deadlineLabel;
	
	private final Button editButton;
	
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
		this.editButton = new Button();
	}
	
	public NutNoteCell(double width, double height, Consumer<NutNote> editButtonHandler) {
		this.editButtonHandler = editButtonHandler;
		
		configureGrid(width, height);
		configureComponentsPosition();
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
		
		configureButtons(note);
		
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
		columnB.setPercentWidth(15);
		
		ColumnConstraints columnC = new ColumnConstraints();
		columnC.setPercentWidth(15);
		
		ColumnConstraints columnD = new ColumnConstraints();
		columnD.setPercentWidth(50);
		
		ColumnConstraints columnE = new ColumnConstraints();
		columnE.setPercentWidth(10);
		
		this.grid.getColumnConstraints().addAll(columnA, columnB, 
				columnC, columnD, columnE);
	}
	
	private void configureComponentsPosition() {
		this.grid.add(this.iconLabel, 0, 0, 1, 3);
		this.grid.add(this.deadlineLabel, 1, 0, 1, 3);
		this.grid.add(this.titleHeaderLabel, 2, 0);
		this.grid.add(this.titleLabel, 3, 0);
		this.grid.add(this.ownerHeaderLabel, 2, 1);
		this.grid.add(this.ownerLabel, 3, 1);
		this.grid.add(this.categoryHeaderLabel, 2, 2);
		this.grid.add(this.categoryLabel, 3, 2);
		this.grid.add(this.editButton, 4, 0, 1, 3);
	}
	
	private void configureHeaderLabels() {
		this.titleHeaderLabel.setText("Title:");
		this.titleHeaderLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
		this.ownerHeaderLabel.setText("Owner:");
		this.ownerHeaderLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
		this.categoryHeaderLabel.setText("Category:");
		this.categoryHeaderLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
	}
	
	private void configureButtons(NutNote note) {
		this.editButton.setText("Edit");
		this.editButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				editButtonHandler.accept(note);
			}
		});
	}
}
