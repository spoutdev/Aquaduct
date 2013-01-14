/*
 * This file is part of Aquaduct.
 *
 * Copyright (c) 2012-2013, Spout LLC <http://www.spout.org/>
 * Aquaduct is licensed under the GNU Affero General Public License.
 *
 * Aquaduct is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Aquaduct is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spout.aquaduct;

import java.util.LinkedList;
import java.util.List;

import org.spout.api.exception.ConfigurationException;

import org.spout.aquaduct.server.Server;
import org.spout.aquaduct.web.AquaductWebServer;

public class Aquaduct {
	private static Aquaduct instance = null;
	private AquaductConfiguration configuration;

	public static Aquaduct instance() {
		return instance;
	}

	private AquaductWebServer webServer;
	private List<Server> servers = new LinkedList<Server>();

	Aquaduct () {
		instance = this;
		configuration = new AquaductConfiguration();
		try {
			configuration.load();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		webServer = new AquaductWebServer();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				Aquaduct.instance().stop();
			}
		});
	}

	public void stop() {
		webServer.stop();
	}

	public List<Server> getServers() {
		return servers;
	}

	public AquaductWebServer getWebServer() {
		return webServer;
	}

	public AquaductConfiguration getConfiguration() {
		return configuration;
	}
}
