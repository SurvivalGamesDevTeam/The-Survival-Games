/**
 * Name: SGWorldCreator.java Edited: 21 January 2014
 *
 * @version 1.0.0
 */

package co.q64.survivalgames.multiworld;

public class SGWorldCreator {

	WorldDefaults ws;

	public SGWorldCreator(String name) {
		this.ws = new WorldDefaults(name);

	}

	public WorldDefaults getWs() {
		return ws;
	}
}
