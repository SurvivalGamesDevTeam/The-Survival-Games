/**
 * Name: KickCommand.java Created: 8 December 2013
 *
 * @version 1.0.0
 */

package co.q64.survivalgames.command.subcommands.party;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.q64.survivalgames.command.subcommands.SubCommand;
import co.q64.survivalgames.locale.I18N;
import co.q64.survivalgames.managers.PartyManager;
import co.q64.survivalgames.managers.SGApi;
import co.q64.survivalgames.objects.Party;

public class KickCommand implements SubCommand {

	/**
	 * Kicks a player out of the party if you are the party leader
	 *
	 * @param sender The player executing the command
	 */
	@Override
	public void execute(String cmd, Player sender, String[] args) {
		if ((args.length == 1) && (args[0].equalsIgnoreCase("kick"))) {
			UUID id = SGApi.getPartyManager().getPlayers().get(sender.getName());
			if (id != null) {
				Party party = SGApi.getPartyManager().getParties().get(id);
				if (party.getLeader().equalsIgnoreCase(sender.getName())) {
					for (String members : party.getMembers()) {
						if ((members != null) && (members.equalsIgnoreCase(args[0]))) {
							party.removeMember(args[0]);
							SGApi.getPartyManager().getPlayers().remove(args[0]);
							sender.sendMessage(org.bukkit.ChatColor.YELLOW + args[0] + I18N.getLocaleString("KICKED_FROM_PARTY"));
							Player p = Bukkit.getServer().getPlayer(args[0]);
							if (p != null) {
								p.sendMessage(org.bukkit.ChatColor.YELLOW + I18N.getLocaleString("KICKED_FROM_PARTY_2"));
							}
							if (party.hasNoMembers()) {
								PartyManager.endParty(sender.getName(), id);
							}
							for (String member : party.getMembers()) {
								if (member != null) {
									Player play = Bukkit.getServer().getPlayer(member);
									if (play != null) {
										play.sendMessage(org.bukkit.ChatColor.YELLOW + args[0] + I18N.getLocaleString("KICKED_FROM_PARTY"));
									}
								}
							}
							return;
						}
					}

					sender.sendMessage(org.bukkit.ChatColor.YELLOW + I18N.getLocaleString("PLAYER") + args[0] + I18N.getLocaleString("NOT_IN_YOUR_PARTY"));
				} else {
					sender.sendMessage(org.bukkit.ChatColor.YELLOW + I18N.getLocaleString("LEADER_TO_KICK"));
				}
			} else {
				sender.sendMessage(org.bukkit.ChatColor.YELLOW + I18N.getLocaleString("NO_PARTY_2"));
			}
		}
	}
}
