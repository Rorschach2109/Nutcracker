package com.nutcracker.app.controller;

import java.lang.ref.WeakReference;

import com.nutcracker.app.util.NutRemoteProxy;

public abstract class AbstractNutController implements INutController {

	protected final NutAppController nutAppController;
	protected final NutRemoteProxy remoteProxy;
	
	private WeakReference<INutController> parentController;

	public AbstractNutController(NutAppController nutAppController, NutRemoteProxy remoteProxy) {
		this.nutAppController = nutAppController;
		this.remoteProxy = remoteProxy;
	}
	
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
