package com.nutcracker.app.controller;

import java.time.LocalDate;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.util.TextValidator;
import com.nutcracker.model.NutNote;

public final class NutReminderAddController extends AbstractNutNoteDetailsController {

	public NutReminderAddController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		super(nutAppController, remoteProxy);
	}
	
	@Override
	protected final boolean validateNote(NutNote note) {
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
		
		if (false == this.editMode && true == noteExists(note)) {
			this.noteDetailsView.showErrorMessage("Note already exists");
			return false;
		}
		
		return true;
	}
}
