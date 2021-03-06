package co.q64.survivalgames.net;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import co.q64.survivalgames.exception.ArenaNotFoundException;
import co.q64.survivalgames.managers.SGApi;
import co.q64.survivalgames.objects.PlayerData;
import co.q64.survivalgames.objects.SGArena;
import co.q64.survivalgames.util.EconUtil;

public class SendWebsocketData {
	public static Map<String, String> music = new HashMap<String, String>();
	static Random rnd = new Random();

	public static String getRandomMusic(String key) {
		File soundcolud = new File(SGApi.getPlugin().getDataFolder(), "soundcloud.yml");
		if (!soundcolud.exists()) {
			SGApi.getPlugin().saveResource("soundcloud.yml", false);
		}
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(soundcolud);
		List<Integer> list = cfg.getIntegerList(key);
		return String.valueOf(list.get(rnd.nextInt(list.size())));
	}

	public static String join(List<UUID> list, String delim) {

		StringBuilder sb = new StringBuilder();

		String loopDelim = "";

		for (UUID s : list) {

			sb.append(loopDelim);
			sb.append(Bukkit.getPlayer(s).getName());

			loopDelim = delim;
		}

		return sb.toString();
	}

	public static void playMusicToPlayer(Player p, String data) {
		try {
			if (!checkValidSession(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName())))
				return;

			if (!SGApi.getPlugin().getPluginConfig().getUseServers())
				return;
			music.remove(p.getName());
			WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "music:" + data);
		} catch (NullPointerException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not communitcate with web socket sound server.");
		}
	}

	public static void playToAll(final String data) {
		try {
			if (!SGApi.getPlugin().getPluginConfig().getUseServers())
				return;
			Bukkit.getScheduler().scheduleSyncDelayedTask(SGApi.getPlugin(), new Runnable() {

				@Override
				public void run() {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()) != null) {
							WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "sound:" + data);
						}
					}
				}
			});
		} catch (NullPointerException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not communitcate with web socket sound server.");
		}

	}

	public static void playToArena(final SGArena arena, final String data) {
		try {
			if (!SGApi.getPlugin().getPluginConfig().getUseServers())
				return;
			Bukkit.getScheduler().scheduleSyncDelayedTask(SGApi.getPlugin(), new Runnable() {

				@Override
				public void run() {
					for (UUID s : arena.getPlayers()) {
						Player p = Bukkit.getPlayer(s);
						if (WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()) != null) {
							WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "sound:" + data);
						}
					}
				}
			});
		} catch (NullPointerException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not communitcate with web socket sound server.");
		}

	}

	public static void playToPlayer(final Player p, final String data) {
		try {
			if (!checkValidSession(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName())))
				return;
			if (!SGApi.getPlugin().getPluginConfig().getUseServers())
				return;
			Bukkit.getScheduler().scheduleSyncDelayedTask(SGApi.getPlugin(), new Runnable() {

				@Override
				public void run() {
					if (WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()) != null) {
						WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "sound:" + data);
					}
				}
			});
		} catch (NullPointerException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not communitcate with web socket sound server.");
		}

	}

	public static void stopMusic(Player p) {
		try {
			if (!SGApi.getPlugin().getPluginConfig().getUseServers())
				return;
			WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "stop");
		} catch (NullPointerException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not communitcate with web socket sound server.");
		}
	}

	public static void updateArenaStatusForPlayer(final Player p) {
		try {
			if (!checkValidSession(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName())))
				return;
			if (!SGApi.getPlugin().getPluginConfig().getUseServers())
				return;
			Bukkit.getScheduler().scheduleSyncDelayedTask(SGApi.getPlugin(), new Runnable() {

				@Override
				public void run() {
					PlayerData data = SGApi.getPlugin().getPlayerData(p);
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "points:" + EconUtil.getPoints(p));
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "kills:" + data.getKills());
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "wins:" + data.getWins());
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "rank:" + ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', data.getRank())));
					SGArena a = null;
					try {
						a = SGApi.getArenaManager().getArena(p);
					} catch (ArenaNotFoundException e) {
						return;
					}
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "arena:" + a.getId());
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "state:" + a.getState());
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "max:" + a.getMaxPlayers());
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "min:" + a.getMinPlayers());
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "players:" + a.getPlayers().size());
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "alive:" + join(a.getPlayers(), "<br>"));
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "dead:" + join(a.getSpectators(), "<br>"));
					WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), "specs:" + join(a.getSpectators(), "<br>"));
				}
			});
		} catch (NullPointerException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not communitcate with web socket sound server.");
		}

	}

	public static boolean checkValidSession(WebsocketSession session) {
		if (session == null) {
			return false;
		}
		return true;
	}
}
