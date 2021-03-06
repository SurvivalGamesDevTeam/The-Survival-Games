package co.q64.survivalgames.enchantment;

import org.bukkit.ChatColor;

public class DedicationEnchantment extends SGEnchantment {

	public DedicationEnchantment(int id) {
		super(id);
	}

	@Override
	public String getLore(int lvl) {
		return ChatColor.GRAY + "Dedication " + RomanNumeral.convert(lvl);
	}

	@Override
	public String getName() {
		return "DEDICATION";
	}
}