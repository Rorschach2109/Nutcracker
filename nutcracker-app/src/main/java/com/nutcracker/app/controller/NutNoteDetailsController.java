package com.nutcracker.app.controller;

import java.time.LocalDate;
import java.util.List;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.util.TextValidator;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.NutNoteDetailsView;
import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.remote.NutcrackerGetterRemote;
import com.nutcracker.remote.NutcrackerSetterRemote;

import javafx.stage.Stage;

public class NutNoteDetailsController extends AbstractNutController {

	private NutNoteDetailsView noteDetailsView;
	private Stage viewStage;

	private final NutAppController nutAppController;
	private final NutRemoteProxy remoteProxy;
	
	public NutNoteDetailsController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		this.nutAppController = nutAppController;
		this.remoteProxy = remoteProxy;
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
		this.noteDetailsView = (NutNoteDetailsView) view;
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
