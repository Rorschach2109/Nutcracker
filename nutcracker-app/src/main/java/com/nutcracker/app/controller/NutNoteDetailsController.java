package com.nutcracker.app.controller;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.NutNoteDetailsView;
import com.nutcracker.model.NutNote;
import com.nutcracker.remote.NutcrackerSetterRemote;

public class NutNoteDetailsController implements INutController {

	private NutNoteDetailsView noteDetailsView;
	private final NutAppController nutAppController;
	private final NutRemoteProxy remoteProxy;
	
	public NutNoteDetailsController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		this.nutAppController = nutAppController;
		this.remoteProxy = remoteProxy;
	}
	
	public void createNewNote(NutNote note, int categoryId, int placeId) {
		NutcrackerSetterRemote nutSetter = remoteProxy.getNutSetter();
		nutSetter.insertNote(nutAppController.getCurrentUserId(), note, categoryId, placeId);
	}
	
	@Override
	public void setView(INutView view) {
		this.noteDetailsView = (NutNoteDetailsView) view;
	}
	
}
