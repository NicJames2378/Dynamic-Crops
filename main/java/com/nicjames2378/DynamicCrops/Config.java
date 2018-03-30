package com.nicjames2378.DynamicCrops;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.google.common.collect.ImmutableList;
import com.nicjames2378.DynamicCrops.proxy.CommonProxy;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

public class Config {

    private static final String CATEGORY_GENERAL = "General";
    private static final String CATEGORY_WHITELIST = "Whitelist";

    // This values below you can access elsewhere in your mod:
    //public static boolean isThisAGoodTutorial = true;
    //public static String yourRealName = "Steve";
    public static String version = "1A";
    
    public static boolean resetWhitelist = false;
    public static String[] whitelist = new String[0];
    
    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
            initWhiteListConfig(cfg);
        } catch (Exception e1) {
            Main.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            //if (cfg.hasChanged()) {

        }
        
    	checkForChanges(cfg);
    	Main.logger.info("RWL: Saving Config");
        cfg.save();
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "Settings pertaining to the mod in general");
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        
        //isThisAGoodTutorial = cfg.getBoolean("goodTutorial", CATEGORY_GENERAL, isThisAGoodTutorial, "Set to false if you don't like this tutorial");
        //yourRealName = cfg.getString("realName", CATEGORY_GENERAL, yourRealName, "Set your real name here");
        version = cfg.getString("Version", CATEGORY_GENERAL, version, "Internal versioning for the config file. DO NOT CHANGE!");
    }

    private static void initWhiteListConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_WHITELIST, "Blocks listed here will have seeds dynamically generated for them");
        resetWhitelist = cfg.getBoolean("Reset Whitelist to vanilla values", CATEGORY_WHITELIST,  false, "Set to true to revert the list to the default vanilla items");
    	whitelist = cfg.getStringList("Whitelist", CATEGORY_WHITELIST, whitelist, "Whitelisted items, blocks, etc, for crops.");
    }
    
    private static void checkForChanges(Configuration cfg) {
        Main.logger.info("RWL: " + resetWhitelist);
    	if (resetWhitelist == true) {
        	whitelist = prepWhitelist();
        	resetWhitelist = false;
        }
    	cfg.save();
    }
    
    private static String[] prepWhitelist() {
    	List<String> list = new ArrayList<String>();    	
    	List<Item> itemsList = ImmutableList.copyOf(Item.REGISTRY);
    	
    	for (Item i : itemsList) {
    		if ( i.getRegistryName().toString().toLowerCase().contains("minecraft".toLowerCase()) ) {
    			Main.logger.info("Item: " + i.getRegistryName());
    			list.add(i.getRegistryName().toString());
    		}
    	}
    	
        String[] stringArray = list.toArray(new String[0]);        
        return stringArray;
    }
}