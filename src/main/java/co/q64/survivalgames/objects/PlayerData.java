//PLEASE DON'T EDIT THIS CLASS - Quantum64

package co.q64.survivalgames.objects;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.q64.survivalgames.util.PlayerNameUtil;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name = "sg_player")
public class PlayerData {

	//
	// Start persistence code
	// WARNING: DO NOT EDIT
	//
	@Id
	private int id;

	@NotNull
	private int kills;
	@NotNull
	private String playerID;
	@NotNull
	private int points;
	@NotEmpty
	private String rank;
	@NotNull
	private int wins;

	public PlayerData() {
		//TODO Just no...
	}

	public PlayerData(Player p) {
		this.playerID = p.getUniqueId().toString();
		this.kills = 0;
		this.points = 0;
		this.rank = "&7M";
		if (PlayerNameUtil.getDevs().contains(p.getName()) || PlayerNameUtil.getAwesomePeople().contains(p.getName())) {
			this.setRank("&d&lDeveloper");
		}
	}

	public void addKill() {
		setKills(getKills() + 1);
	}

	public void addKills(int kills) {
		setKills(getKills() + kills);
	}

	public void addPoints(int points) {
		setPoints(getPoints() + points);
	}

	public void addWin() {
		setWins(getWins() + 1);
	}

	public void addWins(int wins) {
		setKills(getKills() + wins);
	}

	public int getId() {
		return id;
	}

	public int getKills() {
		return kills;
	}

	public Player getPlayer() {
		return Bukkit.getServer().getPlayer(UUID.fromString(playerID));
	}

	public String getPlayerName() {
		return Bukkit.getPlayer(UUID.fromString(playerID)).getName();
	}
	
	public String getPlayerID(){
		return playerID;
	}

	public int getPoints() {
		return points;
	}

	public String getRank() {
		return rank;
	}

	public int getWins() {
		return wins;
	}

	//
	// End persistence code
	//

	public void removeKills(int kills) {
		setKills(getKills() - kills);
	}

	public void removePoints(int points) {
		setPoints(getPoints() - points);
	}

	public void removeWins(int wins) {
		setKills(getKills() - wins);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public void setPlayer(Player player) {
		this.playerID = player.getUniqueId().toString();
	}

	public void setPlayerName(String ply) {
		this.playerID = Bukkit.getPlayer(ply).getUniqueId().toString();
	}
	
	public void setPlayerID(String plyID){
		this.playerID = plyID;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setRank(String rank) {
		if (rank.length() > 16)
			rank = rank.substring(0, 16);
		this.rank = rank;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}
}
