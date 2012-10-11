package org.spout.flo.server;

import org.spout.api.signal.Signal;
import org.spout.api.signal.SignalSubscriberObject;

public abstract class Server extends SignalSubscriberObject {
	/**
	 * Emitted when the servers status changes.
	 * @sarg org.spout.flo.server.ServerStatus the new status of the server
	 */
	public static final Signal SIGNAL_STATUS_CHANGE = new Signal("STATUS_CHANGE", ServerStatus.class);
	
	/**
	 * Emitted when the servers console printed a new message
	 * @sarg java.lang.String the message
	 */
	public static final Signal SIGNAL_CONSOLE_MESSAGE = new Signal("CONSOLE_MESSAGE", String.class);
	
	/**
	 * Emitted when an error occurs that should be sent to watching web frontends
	 * @sarg java.lang.String the message
	 */
	public static final Signal SIGNAL_ERROR = new Signal("ERROR", String.class);
	
	private String name;
	
	public Server() {
		registerSignal(SIGNAL_CONSOLE_MESSAGE);
		registerSignal(SIGNAL_STATUS_CHANGE);
	}
	
	public String getName() {
		return name;
	}
	
	public String getVersion() {
		return "0";
	}
	
	public abstract PluginManager getPluginManager();
	
	public abstract ServerStatus getStatus();
	
	public abstract void start();
	
	public abstract void stop();
	
	
}
