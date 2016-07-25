package com.nutcracker.app.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.NutMainView;
import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.remote.NutcrackerFinderRemote;
import com.nutcracker.remote.NutcrackerGetterRemote;

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
	
	public void generateFutureContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesAfterDate(
				nutAppController.getCurrentUserId(), LocalDateTime.now());
		
		generateContent(notes,  NutNote.class);
	}
	
	public void generatePastContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesBeforeDate(
				nutAppController.getCurrentUserId(), LocalDateTime.now());
		
		generateContent(notes,  NutNote.class);	
	}
	
	public void generateNotesContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesWithoutDeadline(nutAppController.getCurrentUserId());
		
		generateContent(notes, NutNote.class);
	}
	
	public void generateCategoriesContent() {
		NutcrackerGetterRemote nutGetter = remoteProxy.getNutGetter();
		List<NutCategory> userCategories = nutGetter.getUserCategories(nutAppController.getCurrentUserId());
		
		//insertOtherUsersCategories(userCategories);
		
		generateContent(userCategories, NutCategory.class);
	}
	
	@Override
	public void setView(INutView view) {
		this.mainView = (NutMainView) view;
	}
	
	private List<String> getOtherUsersNames() {
		NutcrackerGetterRemote nutGetter = remoteProxy.getNutGetter();
		
		return nutGetter.getUsersLogins().stream()
				.filter(login -> !login.equals(nutAppController.getCurrentUserLogin()))
				.collect(Collectors.toList());
	}
	
	private void insertOtherUsersCategories(List<NutCategory> categories) {
		categories.add(new NutCategory());
		for (String login : getOtherUsersNames()) {
			categories.add(new NutCategory(login));
		}
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
