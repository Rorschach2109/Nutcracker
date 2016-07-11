package com.adrznej.nutcracker.controller.remote;

import java.util.List;

import javax.ejb.Remote;

import com.adrznej.nutcracker.model.NoteModel;

@Remote
public interface NoteControllerRemote {
	public List<NoteModel> getGlobalAvailableNotes(String userLogin);
	
	public void updateNote(NoteModel note);
	
	public void getNotesByPlace(String userLogin, String place);
}
