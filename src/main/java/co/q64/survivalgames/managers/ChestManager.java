package co.q64.survivalgames.managers;

import co.q64.survivalgames.objects.SGArena;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.io.File;
import java.util.List;
import java.util.Random;

public class ChestManager {

	FileConfiguration chests;

	int lvlup = 0;
	Random r = new Random();

	int slot = 0;

	public ChestManager() {
		File chest = new File(SGApi.getPlugin().getDataFolder(), "chests.yml");
		if (!chest.exists()) {
			SGApi.getPlugin().saveResource("chests.yml", false);
		}

		chests = YamlConfiguration.loadConfiguration(chest);

		slot = chests.getInt("slot-probability");
		lvlup = chests.getInt("lvlup-probability");

		Validate.notNull(slot, "You deleted the 'slot-probability' option in the chest config  :(  Why?");
		Validate.notNull(lvlup, "You deleted the 'lvlup-probability' option in the chest config  :(  Why?");
	}

	private String anyItem(List<String> list) {
		int index = r.nextInt(list.size());
		String s = list.get(index);
		return s;
	}

	public void fillChest(SGArena a, Chest chest) {
		if (a.getLooted().contains(chest))
			return;
		a.getLooted().add(chest);
		for (int i = 0; i < 27; i++) {
			if (r.nextInt(100) < slot) {
				if (r.nextInt(100) < lvlup) {
					if (r.nextInt(100) < lvlup) {
						if (r.nextInt(100) < lvlup) {
							if (r.nextInt(100) < lvlup) {
								chest.getInventory().setItem(i, getRandomItemStack(1, 5));
								continue;
							}
							chest.getInventory().setItem(i, getRandomItemStack(1, 4));
							continue;
						}
						chest.getInventory().setItem(i, getRandomItemStack(1, 3));
						continue;
					}
					chest.getInventory().setItem(i, getRandomItemStack(1, 2));
					continue;
				}
				chest.getInventory().setItem(i, getRandomItemStack(1, 1));
				continue;
			}
		}
	}

	public void fillDoubleChest(SGArena a, DoubleChest chest) {
		if (a.getdLooted().contains(chest))
			return;
		a.getdLooted().add(chest);
		for (int i = 0; i < 54; i++) {
			if (r.nextInt(100) < slot) {
				if (r.nextInt(100) < lvlup) {
					if (r.nextInt(100) < lvlup) {
						if (r.nextInt(100) < lvlup) {
							if (r.nextInt(100) < lvlup) {
								chest.getInventory().setItem(i, getRandomItemStack(1, 5));
								continue;
							}
							chest.getInventory().setItem(i, getRandomItemStack(1, 4));
							continue;
						}
						chest.getInventory().setItem(i, getRandomItemStack(1, 3));
						continue;
					}
					chest.getInventory().setItem(i, getRandomItemStack(1, 2));
					continue;
				}
				chest.getInventory().setItem(i, getRandomItemStack(1, 1));
				continue;
			}
		}
	}

	private ItemStack getRandomItemStack(int chest, int lvl) {
		return readItemFromString(anyItem(chests.getStringList("t" + chest + "Chest.lvl" + lvl)));
	}

	private ItemStack readItemFromString(String s) {
		String[] l = s.split(",");
		if (l.length == 2) {
            return new ItemStack(Material.valueOf(l[0]), Integer.parseInt(l[1]));
		}
		if (l.length == 3) {
			ItemStack is = new ItemStack(Material.valueOf(l[0]), Integer.parseInt(l[1]));
			MaterialData data = is.getData();
			data.setData(Byte.parseByte(l[2]));
			is.setData(data);
			return is;
		}
		return null;
	}
}
