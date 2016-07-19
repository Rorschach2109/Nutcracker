package com.nutcracker.app.factory;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.nutcracker.remote.NutcrackerFinderRemote;
import com.nutcracker.remote.NutcrackerGetterRemote;
import com.nutcracker.remote.NutcrackerSetterRemote;
import com.nutcracker.remote.NutcrackerUpdaterRemote;

public final class NutRemoteFactory {

	private static boolean initialized;
	private static Context context;
	
	private final static String lookupTemplate; 
	
	static {
		initialized = false;
		lookupTemplate = "ejb:/nutcracker-server/Nutcracker%1$sBean!com.nutcracker.remote.Nutcracker%1$sRemote";
	}
	
	public static void initialize() throws NamingException {
		if (initialized) {
			return;
		}
		
		final Properties jndiProperties = new Properties();
		jndiProperties.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		context = new InitialContext(jndiProperties);
		
		initialized = true;
	}
	
	public static NutcrackerGetterRemote getNutGetter() throws NamingException {
		return (NutcrackerGetterRemote) context.lookup(String.format(lookupTemplate, "Getter"));
	}
	
	public static NutcrackerSetterRemote getNutSetter() throws NamingException {
		return (NutcrackerSetterRemote) context.lookup(String.format(lookupTemplate, "Setter"));
	}
	
	public static NutcrackerFinderRemote getNutFinder() throws NamingException {
		return (NutcrackerFinderRemote) context.lookup(String.format(lookupTemplate, "Finder"));
	}
	
	public static NutcrackerUpdaterRemote getNutUpdater() throws NamingException {
		return (NutcrackerUpdaterRemote) context.lookup(String.format(lookupTemplate, "Updater"));
	}
}
