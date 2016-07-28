package com.nutcracker.app.controller;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.util.TextValidator;
import com.nutcracker.model.NutNote;

public final class NutNoteAddController extends AbstractNutNoteDetailsController {
	
	public NutNoteAddController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		super(nutAppController, remoteProxy);
	}
	
	@Override
	public final boolean validateNote(NutNote note) {
		if (TextValidator.isEmpty(note.getNoteTitle())) {
			this.noteDetailsView.showErrorMessage("Title cannot be empty");
			return false;
		}
		
		if (null == note.getNoteCategory()) {
			this.noteDetailsView.showErrorMessage("Category cannot be empty");
			return false;
		}
		
		if (false == this.editMode && true == noteExists(note)) {
			this.noteDetailsView.showErrorMessage("Note already exists");
			return false;
		}
		
		return true;
	}
}
