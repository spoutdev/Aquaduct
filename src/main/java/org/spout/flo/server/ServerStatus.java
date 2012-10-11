package org.spout.flo.server;

/**
 * Represents the current status of a server
 */
public enum ServerStatus {
	/**
	 * When the server is completely shut down
	 */
	STOPPED(false),
	/**
	 * When the server is starting up, but not ready for players yet
	 */
	STARTING(true),
	/**
	 * When the server is running and ready for players
	 */
	STARTED(true),
	/**
	 * When the server is currently shutting down
	 */
	STOPPING(true),
	/**
	 * When the server or the JVM have crashed
	 */
	CRASHED(false),
	/**
	 * When the server doesn't react to status polls
	 */
	UNRESPONSIVE(true), ;

	private final boolean isRunning;
	
	private ServerStatus(boolean running) {
		this.isRunning = running;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
}
