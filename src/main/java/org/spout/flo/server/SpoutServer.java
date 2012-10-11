package org.spout.flo.server;

public class SpoutServer extends Server {
	SpoutPluginManager pluginManager;
	ServerStatus status = ServerStatus.STOPPED;
	
	@Override
	public PluginManager getPluginManager() {
		return pluginManager;
	}

	@Override
	public ServerStatus getStatus() {
		return status;
	}

	@Override
	public void start() {
		if (!getStatus().isRunning()) { // Only start the server if not already started
			emit(SIGNAL_STATUS_CHANGE, ServerStatus.STARTING);
			
		}
	}

	@Override
	public void stop() {
		if (getStatus().isRunning()) { // Only stop the server if not already stopped
			emit(SIGNAL_STATUS_CHANGE, ServerStatus.STOPPING);
			
		}
	}
}
