package com.nicjames2378.DynamicCrops.proxy;

import com.nicjames2378.DynamicCrops.ModBlocks;
import com.nicjames2378.DynamicCrops.utils.Reference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid=Reference.MOD_ID, value = Side.SERVER)

public class CommonProxy {	
	public void PreInit(FMLPreInitializationEvent event) {

		//BLOCKS INIT
		ModBlocks.init();
		
	}
	
	public void Init(FMLInitializationEvent event) {
		
		
		
	}	
	
	public void PostInit(FMLPostInitializationEvent event) {

	}
}
