package com.nutcracker.app.controller;

import java.util.List;

import com.nutcracker.app.layout.NutNoteCell;
import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.NutMainView;
import com.nutcracker.model.NutNote;
import com.nutcracker.remote.NutcrackerFinderRemote;

public class NutMainController implements INutController {

	private NutMainView mainView;
	private final NutAppController nutAppController;
	private final NutRemoteProxy remoteProxy;
	
	public NutMainController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		this.nutAppController = nutAppController;
		this.remoteProxy = remoteProxy;
	}
	
	public void generateRemindersContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesWithDeadline(nutAppController.getCurrentUserId());
		
		this.mainView.generateContent(notes, new NutNoteCell());
	}
	
	public void generateNotesContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesWithoutDeadline(nutAppController.getCurrentUserId());
		
		this.mainView.generateContent(notes, new NutNoteCell());
	}
	
	@Override
	public void setView(INutView view) {
		this.mainView = (NutMainView) view;
	}
}
