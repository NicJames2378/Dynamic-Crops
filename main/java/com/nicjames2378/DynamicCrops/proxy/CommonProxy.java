package com.nicjames2378.DynamicCrops.proxy;

import java.io.File;

import com.nicjames2378.DynamicCrops.Main;
import com.nicjames2378.DynamicCrops.blocks.ModBlocks;
import com.nicjames2378.DynamicCrops.utils.Reference;
import com.nicjames2378.DynamicCrops.config.Configurator;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid=Reference.MOD_ID, value = Side.SERVER)
public class CommonProxy {	
	// Config instance
    public static Configuration config;	
	
	public void PreInit(FMLPreInitializationEvent event) {
		Main.logger.info("Proxy PreInit");
        //File directory = event.getModConfigurationDirectory();
        //config = new Configuration(new File(directory.getPath(), Reference.CONFIG_NAME));
        //Config.readConfig();
		Configurator.CheckIfChangesNeeded();
	}
	
	public void Init(FMLInitializationEvent event) {
		Main.logger.info("Proxy Init");
	}	
	
	public void PostInit(FMLPostInitializationEvent event) {
		Main.logger.info("Proxy PostInit");
		//if (config.hasChanged()) {
        //    config.save();
        //}
	}
	
	public void registerItemRenderer(Item item, int meta, String id) {
		Main.logger.info("Proxy RegisterItemRenderer");
	}
}
