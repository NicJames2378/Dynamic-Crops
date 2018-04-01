// https://mcforge.readthedocs.io/en/latest/config/annotations/
package com.nicjames2378.DynamicCrops.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID, name = Reference.MOD_ID, category = "")
public class Configurator {
	//=-=-=-=-=-=-=-=-=-=-=-=-=- START CATEGORIES =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// This section is for declaring "Categories". Each category gets it's own section in the config file and
	//   it's own button on the mod's config page in-game.
	
	@Name("General Settings")
    @Comment("Settings that effect the mod as a whole.")
	public static final Cat_General CATEGORY_GENERAL = new Cat_General();
	
	@Name("Whitelist Settings")
    @Comment("Settings to determine what to make seeds for.")
	public static final Cat_Whitelist CATEGORY_WHITELIST = new Cat_Whitelist();
    //=-=-=-=-=-=-=-=-=-=-=-=-=- END CATEGORIES =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    
	//=-=-=-=-=-=-=-=-=-=-=-=-=- START CAT CLASSES =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// This section is for defining what will be in each category. This effectively makes 'pages' of config options. 
	
	public static class Cat_General {
		@Name("Reset Whitelist")
		@Comment("Revert the whitelist to only enable vanilla items")
		public boolean resetWhitelist = true;
	}
	
	public static class Cat_Whitelist {
		@Name("Whitelisted Items")
		@Comment("Items to generate seeds for.")
		@RequiresMcRestart()
		public String[] whitelist = new String[0];
		
		@Name("Override Seed Names")
		@Comment({"Replace the in-game names for specific seeds. (Must be changed in ",
		          "the config file manually! Use as 'registryName=newName'.)"})
		public Map<String,String> overriddenNames = new HashMap<String, String>();
	}
	//=-=-=-=-=-=-=-=-=-=-=-=-=- END CAT CLASSES =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	
	
	// Code below this point is not strictly for making the config, but rather validating and setting values in code for defaults, etc.
    public static void CheckIfChangesNeeded() {
    	Main.logger.info("Configurator: Checking for needed changes!");
    	boolean hasChanged = false;
    	
    	if (CATEGORY_GENERAL.resetWhitelist) {
    		resetWhitelist();
    		CATEGORY_GENERAL.resetWhitelist = false;
    		hasChanged = true;
    	}
    	
    	if (hasChanged) {
            ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
    	}
    }
	
    // Sets the whitelist to only include vanilla items. 
	public static void resetWhitelist() {
    	List<String> list = new ArrayList<String>();
    	List<Item> itemsList = ImmutableList.copyOf(Item.REGISTRY);
    	
    	for (Item i : itemsList) {
    		if ( i.getRegistryName().toString().toLowerCase().contains("minecraft".toLowerCase()) ) {
    			Main.logger.info("Item: " + i.getRegistryName());
    			list.add(i.getRegistryName().toString());
    		}
    	}
    	
    	list = pruneWhitelist(list);
    	setOverriddenNames();
    	
    	Configurator.CATEGORY_WHITELIST.whitelist = list.toArray(new String[0]);
    }
	
	public static void setOverriddenNames() {
		// Nothing to see here...
	}

	public static List<String> pruneWhitelist(List list) {
		// Don't make seeds for these things. Trust me, just don't.
		// TODO: Add items here that require special data to be functional (ex: spawners), or that just don't make sense to have seeds for (snow_layer, anyone?)
		list.remove("minecraft:air");
		list.remove("minecraft:bedrock");
		list.remove("minecraft:mob_spawner");
		list.remove("minecraft:snow_layer");
		list.remove("minecraft:monster_egg");
		//list.remove("minecraft:end_portal_frame");
		//list.remove("minecraft:dragon_egg");
		list.remove("minecraft:command_block");
		list.remove("minecraft:barrier");
		list.remove("minecraft:grass_path");
		list.remove("minecraft:repeating_command_block");
		list.remove("minecraft:chain_command_block");
		list.remove("minecraft:structure_void");
		list.remove("minecraft:spawn_egg");
		//list.remove("minecraft:writable_book");
		//list.remove("minecraft:map");
		list.remove("minecraft:skull");
		//list.remove("minecraft:fireworks");
		list.remove("minecraft:enchanted_book");
		list.remove("minecraft:command_block_minecart");
		//list.remove("minecraft:banner");
		list.remove("minecraft:splash_potion");
		list.remove("minecraft:spectral_arrow");
		list.remove("minecraft:tipped_arrow");
		list.remove("minecraft:lingering_potion");
		
		return list;
	}
	
	
	// Used to let the Config GUI in the mods menu save to the .cfg file.
    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    private static class Handler
    {
    	@SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(Reference.MOD_ID))
            {
                ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}