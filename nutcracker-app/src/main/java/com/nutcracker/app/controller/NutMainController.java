package com.nutcracker.app.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nutcracker.app.state.controller.main.INutMainControllerState;
import com.nutcracker.app.state.controller.main.NutMainControllerCategoryState;
import com.nutcracker.app.state.controller.main.NutMainControllerFutureState;
import com.nutcracker.app.state.controller.main.NutMainControllerIdleState;
import com.nutcracker.app.state.controller.main.NutMainControllerNoteState;
import com.nutcracker.app.state.controller.main.NutMainControllerPastState;
import com.nutcracker.app.state.controller.main.INutMainControllerState.MAIN_CONTROLLER_STATE;
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

public class NutMainController extends AbstractNutController {

	private NutMainView mainView;
	private MAIN_CONTROLLER_STATE currentState;
	
	private final Map<MAIN_CONTROLLER_STATE, INutMainControllerState> stateMap;
	
	private static final String CELL_FACTORY_TEMPLATE;
	
	static {
		CELL_FACTORY_TEMPLATE = "com.nutcracker.app.layout.%sCell";
	}
	
	{
		this.stateMap = initStateMap();
		this.currentState = MAIN_CONTROLLER_STATE.IDLE;
	}
	
	public NutMainController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		super(nutAppController, remoteProxy);
	}
	
	public void generateFutureContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesAfterDate(
				nutAppController.getCurrentUserId(), LocalDate.now());
		Collections.sort(notes);
		
		generateContent(notes, NutNote.class);
		this.currentState = MAIN_CONTROLLER_STATE.FUTURE;
	}
	
	public void generatePastContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesBeforeDate(
				nutAppController.getCurrentUserId(), LocalDate.now());
		Collections.sort(notes);
		
		generateContent(notes,  NutNote.class);
		this.currentState = MAIN_CONTROLLER_STATE.PAST;
	}
	
	public void generateNotesContent() {
		NutcrackerFinderRemote nutFinder = remoteProxy.getNutFinder();
		List<NutNote> notes = nutFinder.findNotesWithoutDeadline(nutAppController.getCurrentUserId());
		Collections.sort(notes);
		
		generateContent(notes, NutNote.class);
		this.currentState = MAIN_CONTROLLER_STATE.NOTE;
	}
	
	public void generateCategoriesContent() {
		NutcrackerGetterRemote nutGetter = remoteProxy.getNutGetter();
		List<NutCategory> userCategories = nutGetter.getUserCategories(nutAppController.getCurrentUserId());
		Collections.sort(userCategories);
		
		generateContent(userCategories, NutCategory.class);
		this.currentState = MAIN_CONTROLLER_STATE.CATEGORY;
	}
	
	public void showAddFutureWindow() {
		this.nutAppController.showAddFutureWindow();
	}
	
	public void showAddNoteWindow() {
		this.nutAppController.showAddNoteWindow(this);
	}
	
	public void showAddCategoryWindow() {
		this.nutAppController.showAddCategoryWindow(this);
	}
	
	public void updateLayoutList() {
		this.stateMap.get(this.currentState).updateLayoutList(this);
	}
	
	@Override
	public void setView(INutView view) {
		this.mainView = (NutMainView) view;
	}
	
	@Override
	public void updateView() {
		this.stateMap.get(this.currentState).updateLayoutList(this);
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
