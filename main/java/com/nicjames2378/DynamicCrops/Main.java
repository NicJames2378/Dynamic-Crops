package com.nicjames2378.DynamicCrops;

import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;

import com.nicjames2378.DynamicCrops.proxy.CommonProxy;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class Main {

	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	private static Logger logger;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.info("DynamicCrops, ready for action!");
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
}