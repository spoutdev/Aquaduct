package org.spout.flo.server;

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
