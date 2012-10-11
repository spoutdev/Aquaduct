package org.spout.flo.web;

import com.narrowtux.blueberry.Address;
import com.narrowtux.blueberry.BlueberryWebServer;

public class FloWebServer {
	BlueberryWebServer server;
	
	public FloWebServer() {
		server = new BlueberryWebServer();
		server.bind(new Address(null, 5000)); //TODO configure
		server.setApplicationName("Flo");
		server.getHandlers().add(new ResourceFileHandler());
		server.getHandlers().add(new FloWebSocketHandler());
		server.start();
	}
	
	public void stop() {
		server.stop();
	}
}
