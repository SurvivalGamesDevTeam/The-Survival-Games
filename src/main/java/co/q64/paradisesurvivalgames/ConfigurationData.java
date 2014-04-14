package co.q64.paradisesurvivalgames;import java.util.List;import org.bukkit.configuration.file.FileConfiguration;import co.q64.paradisesurvivalgames.managers.SGApi;public class ConfigurationData {	private final TheSurvivalGames plugin = SGApi.getPlugin();	private FileConfiguration config;	private final List<String> allowed;	public ConfigurationData() {		loadConfig();		this.allowed = SGApi.getPlugin().getConfig().getStringList("breaks-allowed");	}	void loadConfig() {		this.plugin.saveDefaultConfig();		this.plugin.reloadConfig();		this.config = this.plugin.getConfig();	}	public void reloadConfig() {		loadConfig();	}	// CONFIG VALUES START HERE	public boolean allowDoubleJump() {		return this.config.getBoolean("allow-double-jump");	}	public boolean allowDoubleJumpIG() {		return this.config.getBoolean("allow-double-jump-IG");	}	public boolean doBloodEffect() {		return this.config.getBoolean("blood-effect");	}	public int getStartingPoints() {		return this.config.getInt("starting-points");	}	public List<String> getWelcomeMessage() {		return this.config.getStringList("welcome-message");	}	public boolean isBungeecordMode() {		return this.config.getBoolean("bungeecord-mode");	}	public boolean isHub() {		return this.config.getBoolean("hub-server");	}	public int getDefaultPoints() {		return this.config.getInt("default-points");	}	public String getHubWorld() {		return this.config.getString("hub-world");	}	public int getDmTime() {		return this.config.getInt("dm-time");	}	public boolean getUseServers() {		return this.config.getBoolean("enable-servers");	}	public String getServerIP() {		return this.config.getString("server-ip");	}	/**	 * Get list of blocks that can be broken in game	 * 	 * @return the list	 */	public List<String> getAllowedBlockBreaks() {		return this.allowed;	}}