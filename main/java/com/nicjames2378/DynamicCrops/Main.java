package com.nicjames2378.DynamicCrops;

import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;

import com.nicjames2378.DynamicCrops.GUI.UICreativeTab;
import com.nicjames2378.DynamicCrops.baseClasses.BaseBlock;
import com.nicjames2378.DynamicCrops.blocks.ModBlocks;
import com.nicjames2378.DynamicCrops.items.ModItems;
import com.nicjames2378.DynamicCrops.proxy.CommonProxy;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class Main {

	@Instance
	public static Main instance;	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;	
	public static Logger logger;	
	public static CreativeTabs modCreativeTab;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.info("DynamicCrops, ready for action!");
		
		modCreativeTab = new UICreativeTab(CreativeTabs.getNextID(), "creativetab");
		ModBlocks.Initialize();		
		
		proxy.PreInit(event);
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		proxy.Init(event);
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		proxy.PostInit(event);
	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void RegisterBlocks(RegistryEvent.Register<Block> event) {
	    	Main.logger.info("REGISTERING BLOCKS");	    	
	    	ModBlocks.RegisterBlocks(event);  	
	    }
	    
		@SubscribeEvent
	    public static void RegisterItems(RegistryEvent.Register<Item> event) {
	    	Main.logger.info("REGISTERING ITEMS");
	        ModBlocks.RegisterItems(event);
	        ModItems.RegisterItems(event.getRegistry());
	    }
		
		@SubscribeEvent
		public static void RegisterItems(ModelRegistryEvent event) {
			ModItems.registerModels();
		}
	}
}