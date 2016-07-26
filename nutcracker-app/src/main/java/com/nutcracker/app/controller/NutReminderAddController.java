package com.nutcracker.app.controller;

import java.time.LocalDate;
import java.util.List;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.util.TextValidator;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.NutReminderAddView;
import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.remote.NutcrackerGetterRemote;
import com.nutcracker.remote.NutcrackerSetterRemote;

import javafx.stage.Stage;

public class NutReminderAddController extends AbstractNutController {

	private NutReminderAddView noteDetailsView;
	private Stage viewStage;

	public NutReminderAddController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		super(nutAppController, remoteProxy);
	}
	
	public void initialize() {
		updateCategories();
	}
	
	public void setStage(Stage stage) {
		this.viewStage = stage;
	}
	
	public boolean validateNote(NutNote note) {
		if (TextValidator.isEmpty(note.getNoteTitle())) {
			this.noteDetailsView.showErrorMessage("Title cannot be empty");
			return false;
		}
		
		if (null == note.getNoteDeadline()) {
			this.noteDetailsView.showErrorMessage("Date cannot be empty");
			return false;
		}
		
		if (0 > note.getNoteDeadline().compareTo(LocalDate.now())) {
			this.noteDetailsView.showErrorMessage("Date is inappropriate");
			return false;
		}
		
		if (null == note.getNoteCategory()) {
			this.noteDetailsView.showErrorMessage("Category cannot be empty");
			return false;
		}
		
		if (true == noteExists(note)) {
			this.noteDetailsView.showErrorMessage("Note already exists");
			return false;
		}
		
		return true;
	}
	
	public void createNewNote(NutNote note, int categoryId, int placeId) {
		NutcrackerSetterRemote nutSetter = remoteProxy.getNutSetter();
		nutSetter.insertNote(nutAppController.getCurrentUserId(), note, categoryId, placeId);
		
		notifyParent();
	}
	
	public void showAddCategoryWindow() {
		nutAppController.showAddCategoryWindow(this, this.viewStage);
	}
	
	public void closeStage() {
		this.viewStage.close();
	}
	
	@Override
	public void setView(INutView view) {
		this.noteDetailsView = (NutReminderAddView) view;
	}

	@Override
	public void updateView() {
		updateCategories();
	}
	
	private void updateCategories() {
		List<NutCategory> categories = getCategories();
		this.noteDetailsView.insertCategories(categories);
	}
	
	private List<NutCategory> getCategories() {
		NutcrackerGetterRemote nutGetter = remoteProxy.getNutGetter();
		return nutGetter.getUserCategories(nutAppController.getCurrentUserId());
	}

	private boolean noteExists(NutNote note) {
		NutcrackerGetterRemote nutGetter = remoteProxy.getNutGetter();
		List<NutNote> userNotes = nutGetter.getUserNotes(nutAppController.getCurrentUserId());
		return userNotes.contains(note);
	}
}
