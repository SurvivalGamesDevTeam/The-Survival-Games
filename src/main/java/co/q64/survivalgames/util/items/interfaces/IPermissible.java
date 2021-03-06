/**
 * Name: IPermissible.java Edited: 20 January 2014
 *
 * @version 1.0.0
 */

package co.q64.survivalgames.util.items.interfaces;

/**
 * The interface I permissible. Does the item require a permission to use it
 *
 * @author TheCommunitySurvivalGames
 * @version 0.0.1
 */
public interface IPermissible {
	/**
	 * Gets permission to use the item in a string format
	 *
	 * @return the permission
	 */
	String getPermission();

	/**
	 * Has permission. Returns True if the permission has been set
	 *
	 * @return the boolean
	 */
	boolean hasPermission();
}
