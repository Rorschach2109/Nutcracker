package com.nutcracker.app.util;

import javax.naming.NamingException;

import com.nutcracker.app.factory.NutRemoteFactory;
import com.nutcracker.remote.NutcrackerFinderRemote;
import com.nutcracker.remote.NutcrackerGetterRemote;
import com.nutcracker.remote.NutcrackerSetterRemote;
import com.nutcracker.remote.NutcrackerUpdaterRemote;

public final class NutRemoteProxy {

	private NutcrackerFinderRemote nutFinder;
	private NutcrackerGetterRemote nutGetter;
	private NutcrackerUpdaterRemote nutUpdater;
	private NutcrackerSetterRemote nutSetter;
	
	public boolean initialize() {
		try {
			initializeRemoteFactory();
			initializeNutRemoteInterfaces();
		} catch (NamingException e) {
			return false;
		}
		
		return true;
	}
	
	private void initializeRemoteFactory() throws NamingException {
		NutRemoteFactory.initialize();
	}
	
	private void initializeNutRemoteInterfaces() throws NamingException {
		this.nutFinder = NutRemoteFactory.getNutFinder();
		this.nutGetter = NutRemoteFactory.getNutGetter();
		this.nutSetter = NutRemoteFactory.getNutSetter();
		this.nutUpdater = NutRemoteFactory.getNutUpdater();
	}

	public NutcrackerFinderRemote getNutFinder() {
		return nutFinder;
	}

	public NutcrackerGetterRemote getNutGetter() {
		return nutGetter;
	}

	public NutcrackerUpdaterRemote getNutUpdater() {
		return nutUpdater;
	}

	public NutcrackerSetterRemote getNutSetter() {
		return nutSetter;
	}
}
