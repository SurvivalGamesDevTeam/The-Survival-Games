/*
 * Name: Arena.java
 * 
 * Version: 1.0
 *
 * Edited: 22 November 2013
 */

package com.gmail.woodyc40.arenaapi;

import org.bukkit.Location;
 
import java.util.ArrayList;
import java.util.List;
 
/**
 * This is the representation of an Arena object in which one can play a game of SurvivalGames
 *
 * @author Community
 * @version 1.0, 11/22/13
 */

public class Arena{
 
    public int id = 0;
    Location spawn = null;
    List<String> players = new ArrayList<String>();
 
    /**
     * Constructs a new arena based off of a Location and an ID
     * 
     * @param loc The default spawn for the arena
     * @param id The ID the arena will have
     */
    
    public Arena(Location loc, int id){
        this.spawn = loc;
        this.id = id;
    }
 
    /**
     * Gets the ID of the arena
     * 
     * @return The ID of the arena
     */
    
    public int getId(){
        return this.id;
    }
 
    /**
     * Gets the list of players in the arena
     * 
     * @return List of players in the arena
     */
    
    public List<String> getPlayers(){
        return this.players;
    }
}
