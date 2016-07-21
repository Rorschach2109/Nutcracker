package com.nutcracker.app.controller;

import java.util.List;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.NutMainView;
import com.nutcracker.model.NutNote;
import com.nutcracker.remote.NutcrackerFinderRemote;

import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class NutMainController implements INutController {

	private NutMainView mainView;
	private final NutAppController nutAppController;
	private final NutRemoteProxy remoteProxy;
	

	private static final String CELL_FACTORY_TEMPLATE;
	
	static {
		CELL_FACTORY_TEMPLATE = "com.nutcracker.app.layout.%sCell";
	}
	
	public NutMainController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		this.nutAppController = nutAppController;
		this.remoteProxy = remoteProxy;
	}
	
	public void generateRemindersContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesWithDeadline(nutAppController.getCurrentUserId());
		
		generateContent(notes, NutNote.class);
	}
	
	public void generateNotesContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesWithoutDeadline(nutAppController.getCurrentUserId());
		
		generateContent(notes, NutNote.class);
	}
	
	@Override
	public void setView(INutView view) {
		this.mainView = (NutMainView) view;
	}
	
	private <T> void generateContent(List<T> contentList, Class<T> classType) {
		ListView<T> layoutList = createLayoutList(contentList, classType);
		this.mainView.changeLayoutList(layoutList);
	}

	private <T> ListView<T> createLayoutList(List<T> contentList, Class<T> classType) {
		ListView<T> layoutList = new ListView<>();
		
		layoutList.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
            @SuppressWarnings("unchecked")
			@Override public ListCell<T> call(ListView<T> list) {
            	try {
            		return (ListCell<T>) Class
            				.forName(String.format(CELL_FACTORY_TEMPLATE, classType.getSimpleName()))
            				.newInstance();
            	} catch (Exception e) {
            		return null;
            	}
            }
        });
		layoutList.setItems(FXCollections.observableArrayList(contentList));
		
		return layoutList;
	}
}
