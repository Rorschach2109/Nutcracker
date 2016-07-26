package com.nutcracker.app.controller;

import com.nutcracker.app.view.INutView;

public interface INutController {
	default public void setView(INutView view) {
		throw new UnsupportedOperationException();
	}
	
	default public void setParentController(INutController parentController) {
		throw new UnsupportedOperationException();
	}
	
	default public void notifyParent() {
		throw new UnsupportedOperationException();
	}
	
	default public void updateView() {
		throw new UnsupportedOperationException();
	}
}
