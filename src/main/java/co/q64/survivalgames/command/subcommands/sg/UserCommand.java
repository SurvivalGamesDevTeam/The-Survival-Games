package co.q64.survivalgames.command.subcommands.sg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import co.q64.survivalgames.command.subcommands.SubCommand;
import co.q64.survivalgames.managers.SGApi;
import co.q64.survivalgames.objects.PlayerData;

public class UserCommand implements SubCommand {

	@Override
	public void execute(String cmd, Player p, String[] args) {
		if (!p.hasPermission("sg.user") || !p.isOp())
			return;
		// TODO Something is getting Null Pointered here every single time -
		// probably SQL save function, can't be sure
		PlayerData data = SGApi.getPlugin().getPlayerData(Bukkit.getPlayer(args[0]));
		if (args[1].equalsIgnoreCase("points")) {
			if (args[2].equalsIgnoreCase("set")) {
				data.setPoints(Integer.parseInt(args[3]));
				SGApi.getPlugin().setPlayerData(data);
			} else if (args[2].equalsIgnoreCase("add")) {
				data.setPoints(data.getPoints() + Integer.parseInt(args[3]));
				SGApi.getPlugin().setPlayerData(data);
			} else if (args[2].equalsIgnoreCase("remove")) {
				data.setPoints(data.getPoints() - Integer.parseInt(args[3]));
				SGApi.getPlugin().setPlayerData(data);
			}
		} else if (args[1].equalsIgnoreCase("rank")) {
			if (args[2].equalsIgnoreCase("set")) {
				data.setRank(ChatColor.translateAlternateColorCodes('&', args[3]));
				SGApi.getPlugin().setPlayerData(data);
			}
		}
		SGApi.getPlugin().setPlayerData(data);
	}

}
