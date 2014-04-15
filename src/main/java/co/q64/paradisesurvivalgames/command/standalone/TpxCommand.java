package co.q64.paradisesurvivalgames.command.standalone;

import co.q64.paradisesurvivalgames.command.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpxCommand extends CommandHandler {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (sender.isOp()) {
            if (Bukkit.getWorld(args[0]) != null) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.teleport(new Location(Bukkit.getWorld(args[0]), Bukkit.getWorld(args[0]).getSpawnLocation()
                            .getX(), Bukkit.getWorld(args[0]).getSpawnLocation().getY(), Bukkit.getWorld(args[0]).getSpawnLocation().getZ()));
                }
            }
        }
        return false;
    }
}
