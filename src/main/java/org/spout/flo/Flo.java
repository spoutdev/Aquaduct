package org.spout.flo;

import java.util.LinkedList;
import java.util.List;

import org.spout.api.exception.ConfigurationException;
import org.spout.flo.server.Server;
import org.spout.flo.web.FloWebServer;

public class Flo {
	private static Flo instance = null;
	private FloConfiguration configuration;
	
	public static Flo instance() {
		return instance;
	}
	
	private FloWebServer webServer;
	private List<Server> servers = new LinkedList<Server>();
	
	Flo () {
		instance = this;
		configuration = new FloConfiguration();
		try {
			configuration.load();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		webServer = new FloWebServer();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				Flo.instance().stop();
			}
		});
	}
	
	public void stop() {
		webServer.stop();
	}
	
	public List<Server> getServers() {
		return servers;
	}
	
	public FloWebServer getWebServer() {
		return webServer;
	}
	
	public FloConfiguration getConfiguration() {
		return configuration;
	}
}
