package com.nutcracker.app.controller;

import java.util.List;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.view.AbstractNutNoteDetailsView;
import com.nutcracker.app.view.INutView;
import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.remote.NutcrackerGetterRemote;
import com.nutcracker.remote.NutcrackerSetterRemote;

import javafx.stage.Stage;

public abstract class AbstractNutNoteDetailsController extends AbstractNutController {

	protected boolean editMode;
	protected AbstractNutNoteDetailsView noteDetailsView;
	
	private Stage viewStage;
	
	public AbstractNutNoteDetailsController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		super(nutAppController, remoteProxy);
		this.editMode = false;
	}
	
	protected abstract boolean validateNote(NutNote note);
		
	public void setContent(NutNote note) {
		if (null != note) {
			this.noteDetailsView.setContent(note);
			this.editMode = true;
		}
	}
	
	public final void handleConfirmButton(NutNote note) {
		if (validateNote(note)) {
			confirmButtonAction(note);
			closeStage();
			notifyParent();
		}
	}

	public void initialize() {
		updateCategories();
	}
	
	public void setStage(Stage stage) {
		this.viewStage = stage;
	}
	
	public void showAddCategoryWindow() {
		nutAppController.showAddCategoryWindow(this, this.viewStage, null);
	}
	
	public void closeStage() {
		this.viewStage.close();
	}
	
	@Override
	public void setView(INutView view) {
		this.noteDetailsView = (AbstractNutNoteDetailsView) view;
	}

	@Override
	public void updateView() {
		updateCategories();
	}
	
	protected boolean noteExists(NutNote note) {
		NutcrackerGetterRemote nutGetter = remoteProxy.getNutGetter();
		List<NutNote> userNotes = nutGetter.getUserNotes(nutAppController.getCurrentUserId());
		return userNotes.contains(note);
	}
	
	private void updateCategories() {
		List<NutCategory> categories = getCategories();
		this.noteDetailsView.insertCategories(categories);
	}
	
	private List<NutCategory> getCategories() {
		NutcrackerGetterRemote nutGetter = remoteProxy.getNutGetter();
		return nutGetter.getUserCategories(nutAppController.getCurrentUserId());
	}
	
	private void confirmButtonAction(NutNote note) {
		if (this.editMode) {
			
		} else {
			NutcrackerSetterRemote nutSetter = remoteProxy.getNutSetter();
			nutSetter.insertNote(nutAppController.getCurrentUserId(), note);
		}
	}
}
