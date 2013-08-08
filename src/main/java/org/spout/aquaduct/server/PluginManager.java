/*
 * This file is part of Aquaduct.
 *
 * Copyright (c) 2012 Spout LLC <http://www.spout.org/>
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
package org.spout.aquaduct.server;

import java.util.List;

public abstract class PluginManager {
	private Server parent;

	protected PluginManager(Server parent) {
		this.parent = parent;
	}

	/**
	 * Gets the server this plugin manager works on
	 * @return the server of this plugin manager
	 */
	public Server getServer() {
		return parent;
	}

	/**
	 * Enables or disables the given plugin
	 * @param plugin the plugin to enable/disable
	 * @param enabled true to enable, false to disable
	 * @throws PluginException when an error occurs
	 */
	public abstract void setPluginEnabled(Plugin plugin, boolean enabled) throws PluginException;

	/**
	 * Installs a plugin on the server
	 * @param toInstall the plugin to install
	 * @throws PluginException when an error occurs
	 */
	public abstract void installPlugin(Plugin toInstall) throws PluginException;

	/**
	 * Removes a plugin from the server
	 * @param toRemove the plugin to remove
	 * @throws PluginException when an error occurs
	 */
	public abstract void removePlugin(Plugin toRemove) throws PluginException;

	/**
	 * Gets a list of all installed plugins, wether they are enabled or not
	 * @return a list of all installed plugins
	 */
	public abstract List<Plugin> getInstalledPlugins();
}
