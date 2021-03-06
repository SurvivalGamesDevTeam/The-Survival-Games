/**
 * Name: WorldDefaults.java Edited: 20 January 2014
 *
 * @version 1.0.0
 */

package co.q64.survivalgames.multiworld;

import org.bukkit.configuration.file.FileConfiguration;

import co.q64.survivalgames.configs.FileLoader;

public class WorldDefaults {

	FileConfiguration config;
	FileLoader configy;

	public WorldDefaults(String name) {
		configy = new FileLoader("worldSettings");
		config = configy.getConfig();

		config.addDefault("testing.name", "relicum");
		config.addDefault("testing.friend", "Clive");

		configy.saveConfig();

	}

}
