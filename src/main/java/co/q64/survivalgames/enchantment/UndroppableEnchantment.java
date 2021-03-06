package co.q64.survivalgames.enchantment;

import org.bukkit.ChatColor;

public class UndroppableEnchantment extends SGEnchantment {

	public UndroppableEnchantment(int id) {
		super(id);
	}

	@Override
	public String getLore(int lvl) {
		return ChatColor.GRAY + "Undroppable " + RomanNumeral.convert(lvl);
	}

	@Override
	public String getName() {
		return "UNDROPPABLE";
	}
}
