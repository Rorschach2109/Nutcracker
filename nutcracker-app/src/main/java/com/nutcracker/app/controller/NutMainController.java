package com.nutcracker.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nutcracker.app.state.INutMainControllerState;
import com.nutcracker.app.state.NutMainControllerCategoryState;
import com.nutcracker.app.state.NutMainControllerFutureState;
import com.nutcracker.app.state.NutMainControllerIdleState;
import com.nutcracker.app.state.NutMainControllerNoteState;
import com.nutcracker.app.state.NutMainControllerPastState;
import com.nutcracker.app.state.INutMainControllerState.MAIN_CONTROLLER_STATE;
import com.nutcracker.app.util.NutRemoteProxy;
import com.nutcracker.app.view.INutView;
import com.nutcracker.app.view.NutMainView;
import com.nutcracker.app.view.ResourcePathFinder;
import com.nutcracker.model.NutCategory;
import com.nutcracker.model.NutNote;
import com.nutcracker.remote.NutcrackerFinderRemote;
import com.nutcracker.remote.NutcrackerGetterRemote;
import com.nutcracker.remote.NutcrackerSetterRemote;

import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class NutMainController implements INutController {

	private NutMainView mainView;
	private MAIN_CONTROLLER_STATE currentState;
	
	private final Map<MAIN_CONTROLLER_STATE, INutMainControllerState> stateMap;
	private final NutAppController nutAppController;
	private final NutRemoteProxy remoteProxy;
	

	private static final String CELL_FACTORY_TEMPLATE;
	
	static {
		CELL_FACTORY_TEMPLATE = "com.nutcracker.app.layout.%sCell";
	}
	
	{
		this.stateMap = initStateMap();
		this.currentState = MAIN_CONTROLLER_STATE.IDLE;
	}
	
	public NutMainController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		this.nutAppController = nutAppController;
		this.remoteProxy = remoteProxy;
	}
	
	public void generateFutureContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesAfterDate(
				nutAppController.getCurrentUserId(), LocalDate.now());
		
		generateContent(notes, NutNote.class);
		this.currentState = MAIN_CONTROLLER_STATE.FUTURE;
	}
	
	public void generatePastContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesBeforeDate(
				nutAppController.getCurrentUserId(), LocalDate.now());
		
		generateContent(notes,  NutNote.class);
		this.currentState = MAIN_CONTROLLER_STATE.PAST;
	}
	
	public void generateNotesContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesWithoutDeadline(nutAppController.getCurrentUserId());
		
		generateContent(notes, NutNote.class);
		this.currentState = MAIN_CONTROLLER_STATE.NOTE;
	}
	
	public void generateCategoriesContent() {
		NutcrackerGetterRemote nutGetter = remoteProxy.getNutGetter();
		List<NutCategory> userCategories = nutGetter.getUserCategories(nutAppController.getCurrentUserId());
		
		//insertOtherUsersCategories(userCategories);
		
		generateContent(userCategories, NutCategory.class);
		this.currentState = MAIN_CONTROLLER_STATE.CATEGORY;
	}
	
	public void showAddFutureWindow() {
		this.nutAppController.showAddFutureWindow();
	}
	
	public void updateLayoutList() {
		this.stateMap.get(this.currentState).updateLayoutList(this);
	}
	
	@Override
	public void setView(INutView view) {
		this.mainView = (NutMainView) view;
	}
	
	private Map<MAIN_CONTROLLER_STATE, INutMainControllerState> initStateMap() {
		Map<MAIN_CONTROLLER_STATE, INutMainControllerState> stateMap = new HashMap<>();
		
		stateMap.put(MAIN_CONTROLLER_STATE.IDLE, new NutMainControllerIdleState());
		stateMap.put(MAIN_CONTROLLER_STATE.FUTURE, new NutMainControllerFutureState());
		stateMap.put(MAIN_CONTROLLER_STATE.PAST, new NutMainControllerPastState());
		stateMap.put(MAIN_CONTROLLER_STATE.NOTE, new NutMainControllerNoteState());
		stateMap.put(MAIN_CONTROLLER_STATE.CATEGORY, new NutMainControllerCategoryState());
		
		return stateMap;
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
