package com.nutcracker.app.state.controller.main;

import com.nutcracker.app.controller.NutMainController;

public interface INutMainControllerState {
	public enum MAIN_CONTROLLER_STATE {
		IDLE,
		FUTURE,
		PAST,
		NOTE,
		CATEGORY
	};
	
	public void updateLayoutList(NutMainController mainController);
	public <T> void layoutListDoubleClickHandler(NutMainController mainController, T object);
}
