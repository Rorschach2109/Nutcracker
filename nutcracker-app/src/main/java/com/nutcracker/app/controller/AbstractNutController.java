package com.nutcracker.app.controller;

import java.lang.ref.WeakReference;

public abstract class AbstractNutController implements INutController {

	private WeakReference<INutController> parentController;
	
	@Override
	public final void setParentController(INutController parentController) {
		this.parentController = new WeakReference<>(parentController);
	}
	
	@Override
	public final void notifyParent() {
		INutController parent = this.parentController.get();
		
		if (null != parent) {
			parent.updateView();
		}
	}
}
