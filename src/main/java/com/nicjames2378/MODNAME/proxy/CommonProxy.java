package com.nicjames2378.MODNAME.proxy;

import com.nicjames2378.MODNAME.utils.Reference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid=Reference.MOD_ID, value = Side.SERVER)

public class CommonProxy {	
	public void PreInit(FMLPreInitializationEvent event) {
	
	}
	
	public void Init(FMLInitializationEvent event) {
		
	}	
	
	public void PostInit(FMLPostInitializationEvent event) {

	}
}
