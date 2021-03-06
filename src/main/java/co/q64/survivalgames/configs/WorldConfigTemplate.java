package co.q64.survivalgames.configs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.BlockState;

import co.q64.survivalgames.managers.SGApi;
import co.q64.survivalgames.multiworld.SGWorld;

public class WorldConfigTemplate extends ConfigTemplate<SGWorld> {
	private SGWorld cachedWorldCreator;

	private String cachedWorldName;
	private SGWorld world;

	public WorldConfigTemplate(File file) {
		super(file);
	}

	public WorldConfigTemplate(SGWorld world) {
		super("maps/" + world.getWorld().getName() + ".yml");
		this.world = world;
	}

	@Override
	public SGWorld fromFile(int index, Object o) {
		switch (index) {
		case 0:
			this.cachedWorldName = String.valueOf(o);
			break;
		case 1:
			String cachedDisplayName = String.valueOf(o);

			this.cachedWorldCreator = new SGWorld(this.cachedWorldName, cachedDisplayName);
			this.cachedWorldCreator.create();
			break;
		case 2:
			List<Location> locs = new ArrayList<>();
			for (String s : (List<String>) o) {
				locs.add(SGApi.getArenaManager().deserializeLoc(s));
			}
			this.cachedWorldCreator.locs = locs;
			break;
		case 3:
			List<BlockState> list = new ArrayList<>();
			for (String s : (List<String>) o) {
				list.add(SGApi.getArenaManager().deserializeBlock(s).getState());
			}
			this.cachedWorldCreator.t2 = list;
			break;
		case 4:
			if (o != null){
				this.cachedWorldCreator.setGracePeriod(Integer.valueOf(String.valueOf(o)));
			}
			break;
		}
		return this.cachedWorldCreator;
	}

	@Override
	public String[] pattern() {
		return new String[] { "World-name", "Display-name", "Spawns", "Chests", "GracePeriod" };
	}

	@Override
	public Object toFile(int keyPair) {
		switch (keyPair) {
		case 0:
			return this.world.getName();
		case 1:
			return this.world.getDisplayName();
		case 2:
			List<String> stringList = new ArrayList<>();
			for (Location l : this.world.locs) {
				stringList.add(SGApi.getArenaManager().serializeLoc(l));
			}
			return stringList;
		case 3:
			List<String> list = new ArrayList<>();
			for (BlockState b : this.world.t2) {
				list.add(SGApi.getArenaManager().serializeBlock(b.getBlock()));
			}
			return list;
		case 4:
			return this.world.getGracePeriod();
		}
		return null;
	}
}
