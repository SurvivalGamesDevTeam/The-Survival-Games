package co.q64.survivalgames.enchantment;

import org.bukkit.ChatColor;

public class UnenchantableEnchantment extends SGEnchantment {

	public UnenchantableEnchantment(int id) {
		super(id);
	}

	@Override
	public String getLore(int lvl) {
		return ChatColor.GRAY + "Un-Enchantable " + RomanNumeral.convert(lvl);
	}

	@Override
	public String getName() {
		return "UNENCHANTABLE";
	}
}