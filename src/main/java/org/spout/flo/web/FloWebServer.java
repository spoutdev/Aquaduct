package org.spout.flo.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.spout.flo.FloConfiguration;

import com.narrowtux.blueberry.Address;
import com.narrowtux.blueberry.BlueberryWebServer;

public class FloWebServer {
	BlueberryWebServer server;
	FloWebSocketHandler webSocketHandler;
	
	public FloWebServer() {
		server = new BlueberryWebServer();
		try {
			server.bind(new Address(InetAddress.getByName(FloConfiguration.BIND_ADDRESS.getString(null)), FloConfiguration.BIND_PORT.getInt(5000)));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}
		server.setApplicationName("Flo");
		server.getHandlers().add(new ResourceFileHandler());
		webSocketHandler = new FloWebSocketHandler();
		server.getHandlers().add(webSocketHandler);
		server.start();
	}
	
	public FloWebSocketHandler getWebSocketHandler() {
		return webSocketHandler;
	}
	
	public void stop() {
		server.stop();
	}
}
