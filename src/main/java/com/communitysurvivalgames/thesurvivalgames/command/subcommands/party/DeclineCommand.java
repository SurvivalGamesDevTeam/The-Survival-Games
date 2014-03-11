/**
 * Name: DeclineCommand.java Created: 8 December 2013
 *
 * @version 1.0.0
 */
package com.communitysurvivalgames.thesurvivalgames.command.subcommands.party;

import src.main.java.com.communitysurvivalgames.thesurvivalgames.command.subcommands.SubCommand;
import src.main.java.com.communitysurvivalgames.thesurvivalgames.locale.I18N;
import src.main.java.com.communitysurvivalgames.thesurvivalgames.managers.PartyManager;
import src.main.java.com.communitysurvivalgames.thesurvivalgames.managers.SGApi;
import src.main.java.com.communitysurvivalgames.thesurvivalgames.objects.Party;

public class DeclineCommand implements SubCommand {

    /**
     * Declines an invitation to join a party
     * 
     * @param sender The player executing the command
     */
    public void execute(String cmd, Player sender, String[] args) {
        if (cmd.equalsIgnoreCase("decline")) {
            UUID id = SGApi.getPartyManager().getInvites().get(sender.getName());
            if (id != null) {
                Party party = SGApi.getPartyManager().getParties().get(id);
                if (party != null) {
                    Player player = Bukkit.getServer().getPlayer(party.getLeader());
                    if (player != null) {
                        player.sendMessage(org.bukkit.ChatColor.YELLOW + sender.getName() + I18N.getLocaleString("HAS_DECLINED"));
                    }
                    if (party.hasNoMembers()) {
                        PartyManager.endParty(party.getLeader(), id);
                        sender.sendMessage(org.bukkit.ChatColor.YELLOW + I18N.getLocaleString("YOU_DECLINED") + party.getLeader() + I18N.getLocaleString("INVITATION"));
                    }
                }
                SGApi.getPartyManager().getInvites().remove(sender.getName());
            } else {
                sender.sendMessage(org.bukkit.ChatColor.YELLOW + I18N.getLocaleString("NO_INVITE"));
            }

        }
    }
}
